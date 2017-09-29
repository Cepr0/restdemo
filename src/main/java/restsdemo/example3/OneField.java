package restsdemo.example3;

/**
 * @author Cepro, 2017-09-29
 */
public interface OneField<T> {
	default T getValue() {
		return null;
	}
}
