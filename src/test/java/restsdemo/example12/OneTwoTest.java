package restsdemo.example12;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import restsdemo.BaseTest;
import restsdemo.base.LongId;
import restsdemo.example14.BaseEntity;

import javax.persistence.criteria.*;
import java.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.*;

/**
 * @author Cepro
 * @since 2017-07-09
 */
public class OneTwoTest extends BaseTest {

	@Autowired
	private One.Repo oneRepo;

	@Autowired
	private Two.Repo twoRepo;

	@Autowired
	private Three.Repo threeRepo;

	@Before
	public void setUp() throws Exception {

		List<Three> threes = threeRepo.save(asList(
				new Three("three1"),
				new Three("three2"),
				new Three("three3"),
				new Three("three4"),
				new Three("three5")
		));

		List<Two> twos = twoRepo.save(asList(
				new Two("two1", threes.get(0), threes.get(1), threes.get(2)),
				new Two("two2"),
				new Two("two3", threes.get(3), threes.get(4)),
				new Two("two4"),
				new Two("two5")
		));

		oneRepo.save(asList(
				new One("mr.","one1", twos.get(0), twos.get(1)),
				new One("mr.","one2", twos.get(2), twos.get(3), twos.get(4)),
				new One("ms.", "one3")
		));

		oneRepo.flush();
	}

	@Test
	public void findAll() throws Exception {

		List<One> ones = oneRepo.findAll();
		assertThat(ones).hasSize(2);

		ones.forEach(one -> {
			Set<Two> twos = one.getTwos();
			assertThat(twos).isNotNull();
			assertThat(twos.size()).isGreaterThan(0);
			twos.forEach(two -> assertThat(two).isNotNull());
		});

		assertThat(twoRepo.findAll()).hasSize(5);
	}

	@Test
	public void findAllByTwosContains() throws Exception {

		Two two1 = twoRepo.getOne(1L);
		List<One> ones = oneRepo.findAllByTwosContains(two1);
		assertThat(ones).hasSize(1);

		Two two3 = twoRepo.getOne(3L);
		ones = oneRepo.findAllByTwosContains(two3);
		assertThat(ones).hasSize(1);
	}

	@Test
	public void getTwosByOneName() throws Exception {
		List<Two> twos = oneRepo.getTwosByOneName("one1");
		assertThat(twos).hasSize(2);
		assertThat(twos).containsOnly(twoRepo.getOne(1L), twoRepo.getOne(2L));
	}

	@Test
	public void getWithSpecification() throws Exception {

		Specification<One> equalsTwoName = (one, query, cb) -> {
			Join<One, Two> joinTwos = one.join("twos");
			return cb.equal(joinTwos.get("name"), "two2");
		};

		List<One> ones = oneRepo.findAll(equalsTwoName);
		assertThat(ones).hasSize(1);
		assertThat(ones.get(0).getName()).isEqualTo("one1");

		Specification<One> likeOneNameAndTwoName = (one, query, cb) -> {
			Join<One, Two> joinTwos = one.join("twos");
			return cb.and(
					cb.like(one.get("name"), "%1%"),
					cb.like(joinTwos.get("name"), "%2%")
			);
		};

		ones = oneRepo.findAll(likeOneNameAndTwoName);
		assertThat(ones).hasSize(1);
		assertThat(ones.get(0).getName()).isEqualTo("one1");

		Specification<One> twoJoins = (one, query, cb) -> {
			Join<One, Two> twos = one.join("twos");
			twos.alias("t");
			Join<Two, Three> threes = twos.join("threes");
			threes.alias("r");
//            query.orderBy(cb.desc(twos.get("name")));
			query.orderBy(cb.desc(one.get("name")), cb.desc(twos.get("name")), cb.desc(threes.get("title")));
			return threes.get("title").in("three1", "three5");
		};

		Specification<One> pOne = (one, query, cb) -> {
			query.orderBy(cb.desc(one.get("name")));
			return cb.like(one.get("name"), "%one%");
		};

		Specification<One> pTwo = (one, query, cb) -> {
			Join<One, Two> twos = one.join("twos");
			query.orderBy(cb.desc(twos.get("name")));
			return cb.like(twos.get("name"), "%two%");
		};

		Specification<One> pThree = (one, query, cb) -> {
			Join<One, Two> twos = one.join("twos");
			Join<Two, Three> threes = twos.join("threes");
			query.orderBy(cb.desc(threes.get("title")));
			return cb.like(threes.get("title"), "%three%");
		};

		ones = oneRepo.findAll(pOne);
		assertThat(ones).hasSize(2);

		ones = oneRepo.findAll(twoJoins, new Sort(DESC, "name"));
		assertThat(ones).hasSize(2);

		PageRequest pageRequest = new PageRequest(0, 2/*, new Sort(Sort.Direction.DESC, "title")*/);
		Page<One> onePage = oneRepo.findAll(twoJoins, pageRequest);
		assertThat(onePage.getContent()).hasSize(2);
	}

	@Test
	public void getOnes() throws Exception {
		PageRequest pageRequest = new PageRequest(0, 2, new Sort(Sort.Direction.DESC, "name"));
		Page<One> onePage = oneRepo.getOnes("three5", pageRequest);
		assertThat(onePage.getContent()).hasSize(1);
	}

	@Test
	public void getOnesAdTwos() throws Exception {

		List<One> list = oneRepo.getOnesAdTwos();
		assertThat(list).hasSize(5);
	}

	@Test
	public void specificationAndFetch() throws Exception {
		Specification<One> p = (ones, query, cb) -> {
			ones.fetch("twos", JoinType.LEFT);
			Join<Object, Object> tows = ones.join("twos");
			query.distinct(true);
			return cb.like(tows.get("name"), "%two%");
		};

		List<One> ones = oneRepo.findAll(p);
		assertThat(ones).hasSize(2);
		Set<Two> twos0 = ones.get(0).getTwos();
		//assertThat(twos0.size()).isGreaterThan(0);
		String name = twos0.iterator().next().getName();
		assertThat(ones.get(1).getTwos().size()).isGreaterThan(0);
	}

	@Test
	public void getWithTitleAndName() throws Exception {

		List<One> list = oneRepo.getWithTitleAndName("mr.", "one");
		assertThat(list).hasSize(2);

		Specification<One> p = dynamicLike("Mr. One", "title", "name");
		assertThat(oneRepo.findAll(p)).hasSize(2);

		p = dynamicLike2("Mr One", "title", "name");
		List<One> all = oneRepo.findAll(p);
		assertThat(all).hasSize(2);
	}

	private <T> Specification<T> dynamicLike(String value, String... properties) {
		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

			Expression<String> concat = null;

			for (String property : properties) {
				if (concat == null) {
					concat = cb.concat("", root.get(property));
				} else {
					concat = cb.concat(concat, cb.concat(" ", root.get(property)));
				}
			}

			return cb.like(cb.lower(concat), "%" + value.toLowerCase() + "%");
		};
	}

	private <T> Specification<T> dynamicLike2(String value, String... properties) {
		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

			String[] values = value.split("\\s");
			int minNumber = Integer.min(values.length, properties.length);

			Predicate[] likes = new Predicate[minNumber];

			for (int i = 0; i < minNumber; i++) {
				likes[i] = cb.like(cb.lower(root.get(properties[i])), "%" + values[i].toLowerCase() + "%");
			}

			return cb.and(likes);
		};
	}
}