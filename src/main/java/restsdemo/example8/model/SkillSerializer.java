package restsdemo.example8.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * https://stackoverflow.com/a/44719861
 * @author Cepro
 * @since 2017-06-23
 */
public class SkillSerializer extends StdSerializer<Member.MemberSkill> {
    
    public SkillSerializer() {
        this(null);
    }
    
    protected SkillSerializer(Class<Member.MemberSkill> t) {
        super(t);
    }
    
    @Override
    public void serialize(Member.MemberSkill value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("levelNum", value.getLevel());
        gen.writeStringField("skillName", value.getSkill().getName());
        gen.writeEndObject();
    }
}
