package ch.heig.gen;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import picocli.CommandLine;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.*;

public class VersionOption implements CommandLine.IVersionProvider {

    public String getVersionFromPom() throws IOException, XmlPullParserException {
        InputStream inputStream = this.getClass().getResourceAsStream("/project-pom.xml");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(bufferedReader);

        return model.getVersion();
    }

    @Override
    public String[] getVersion() throws Exception {
        String version = getVersionFromPom();
        return new String[]{version};
    }
}
