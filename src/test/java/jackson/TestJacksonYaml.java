package jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class TestJacksonYaml {

    private static final String inputYamlFilepath = "src/test/resources/file-in.yaml";
    private static final String outputYamlFilepath = "src/test/resources/file-out.yaml";
    private static final String inputJsonFilepath = "src/test/resources/file-in.json";
    private static final String outputJsonFilepath = "src/test/resources/file-out.json";

    @Test
    void testReadWriteJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File inputFile = Paths.get(inputJsonFilepath).toFile();
        MainDoc doc = mapper.readValue(inputFile, MainDoc.class);
        assertNotNull(doc);
        assertEquals(createTestObject(), doc);

        File outFile = Paths.get(outputJsonFilepath).toFile();
        mapper.writeValue(outFile, doc);
    }

    @Test
    void testReadWriteYAML() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        File inputFile = Paths.get(inputYamlFilepath).toFile();
        MainDoc doc = mapper.readValue(inputFile, MainDoc.class);
        assertNotNull(doc);
        assertEquals(createTestObject(), doc);

        File outFile = Paths.get(outputYamlFilepath).toFile();
        mapper.writeValue(outFile, doc);
    }

    @Test
    void testCreateAndWrite() throws Exception {
        MainDoc doc = createTestObject();

        ObjectMapper mapperJson = new ObjectMapper();
        File jsonOutputFile = Paths.get(outputJsonFilepath).toFile();
        mapperJson.writeValue(jsonOutputFile, doc);

        ObjectMapper mapperYaml = new ObjectMapper(new YAMLFactory());
        File outputFile = Paths.get(outputYamlFilepath).toFile();
        mapperYaml.writeValue(outputFile, doc);
    }

    private MainDoc createTestObject() {
        Setting1 set1 = new Setting1("sam", "test");
        Profile profile1 = new Profile("profile1", "type1", set1);
        Setting2 set2 = new Setting2("map1");
        Profile profile2 = new Profile("profile2", "type2", set2);
        List<Profile> profiles = new ArrayList<Profile>();
        profiles.add(profile1);
        profiles.add(profile2);
        MainDoc doc = new MainDoc("doc1", profiles);

        return doc;
    }
}
