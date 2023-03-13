package jackson;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use = Id.DEDUCTION)
@JsonSubTypes({ @Type(value = Setting1.class, name = "settings"),
                @Type(value = Setting2.class, name = "settings") })
public interface Setting {

}
