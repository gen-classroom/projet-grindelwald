package ch.heig.gen;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import picocli.CommandLine;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class VersionOption implements CommandLine.IVersionProvider {

    public String getVersionFromPom() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("src/test/resources/project-pom.xml"));
        return model.getVersion();
    }

    @Override
    public String[] getVersion() throws Exception {
        String version = getVersionFromPom();
        return new String[]{version};
    }
}
