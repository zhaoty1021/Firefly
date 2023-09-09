import com.yingxiu.rpc.codec.compress.gzip.GzipCompress;
import com.yingxiu.rpc.codec.compress.snappy.SnappyCompress;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/9
 */
@SpringBootTest(classes = CompressTest.class)
public class CompressTest {
    @Test
    public void compress(){
        String s="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<!--\n" +
                "Licensed to the Apache Software Foundation (ASF) under one\n" +
                "or more contributor license agreements.  See the NOTICE file\n" +
                "distributed with this work for additional information\n" +
                "regarding copyright ownership.  The ASF licenses this file\n" +
                "to you under the Apache License, Version 2.0 (the\n" +
                "\"License\"); you may not use this file except in compliance\n" +
                "with the License.  You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing,\n" +
                "software distributed under the License is distributed on an\n" +
                "\"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY\n" +
                "KIND, either express or implied.  See the License for the\n" +
                "specific language governing permissions and limitations\n" +
                "under the License.\n" +
                "-->\n" +
                "\n" +
                "<!--\n" +
                " | This is the configuration file for Maven. It can be specified at two levels:\n" +
                " |\n" +
                " |  1. User Level. This settings.xml file provides configuration for a single user,\n" +
                " |                 and is normally provided in ${user.home}/.m2/settings.xml.\n" +
                " |\n" +
                " |                 NOTE: This location can be overridden with the CLI option:\n" +
                " |\n" +
                " |                 -s /path/to/user/settings.xml\n" +
                " |\n" +
                " |  2. Global Level. This settings.xml file provides configuration for all Maven\n" +
                " |                 users on a machine (assuming they're all using the same Maven\n" +
                " |                 installation). It's normally provided in\n" +
                " |                 ${maven.conf}/settings.xml.\n" +
                " |\n" +
                " |                 NOTE: This location can be overridden with the CLI option:\n" +
                " |\n" +
                " |                 -gs /path/to/global/settings.xml\n" +
                " |\n" +
                " | The sections in this sample file are intended to give you a running start at\n" +
                " | getting the most out of your Maven installation. Where appropriate, the default\n" +
                " | values (values used when the setting is not specified) are provided.\n" +
                " |\n" +
                " |-->\n" +
                "<settings xmlns=\"http://maven.apache.org/SETTINGS/1.2.0\"\n" +
                "          xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "          xsi:schemaLocation=\"http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd\">\n" +
                "  <!-- localRepository\n" +
                "   | The path to the local repository maven will use to store artifacts.\n" +
                "   |\n" +
                "   | Default: ${user.home}/.m2/repository\n" +
                "  <localRepository>/path/to/local/repo</localRepository>\n" +
                "  -->\n" +
                "  <localRepository>F:/Programs/path/repository</localRepository>\n" +
                "  <!-- interactiveMode\n" +
                "   | This will determine whether maven prompts you when it needs input. If set to false,\n" +
                "   | maven will use a sensible default value, perhaps based on some other setting, for\n" +
                "   | the parameter in question.\n" +
                "   |\n" +
                "   | Default: true\n" +
                "  <interactiveMode>true</interactiveMode>\n" +
                "  -->\n" +
                "\n" +
                "  <!-- offline\n" +
                "   | Determines whether maven should attempt to connect to the network when executing a build.\n" +
                "   | This will have an effect on artifact downloads, artifact deployment, and others.\n" +
                "   |\n" +
                "   | Default: false\n" +
                "  <offline>false</offline>\n" +
                "  -->\n" +
                "\n" +
                "  <!-- pluginGroups\n" +
                "   | This is a list of additional group identifiers that will be searched when resolving plugins by their prefix, i.e.\n" +
                "   | when invoking a command line like \"mvn prefix:goal\". Maven will automatically add the group identifiers\n" +
                "   | \"org.apache.maven.plugins\" and \"org.codehaus.mojo\" if these are not already contained in the list.\n" +
                "   |-->\n" +
                "  <pluginGroups>\n" +
                "    <!-- pluginGroup\n" +
                "     | Specifies a further group identifier to use for plugin lookup.\n" +
                "    <pluginGroup>com.your.plugins</pluginGroup>\n" +
                "    -->\n" +
                "  </pluginGroups>\n" +
                "\n" +
                "  <!-- proxies\n" +
                "   | This is a list of proxies which can be used on this machine to connect to the network.\n" +
                "   | Unless otherwise specified (by system property or command-line switch), the first proxy\n" +
                "   | specification in this list marked as active will be used.\n" +
                "   |-->\n" +
                "  <proxies>\n" +
                "    <!-- proxy\n" +
                "     | Specification for one proxy, to be used in connecting to the network.\n" +
                "     |\n" +
                "    <proxy>\n" +
                "      <id>optional</id>\n" +
                "      <active>true</active>\n" +
                "      <protocol>http</protocol>\n" +
                "      <username>proxyuser</username>\n" +
                "      <password>proxypass</password>\n" +
                "      <host>proxy.host.net</host>\n" +
                "      <port>80</port>\n" +
                "      <nonProxyHosts>local.net|some.host.com</nonProxyHosts>\n" +
                "    </proxy>\n" +
                "    -->\n" +
                "  </proxies>\n" +
                "\n" +
                "  <!-- servers\n" +
                "   | This is a list of authentication profiles, keyed by the server-id used within the system.\n" +
                "   | Authentication profiles can be used whenever maven must make a connection to a remote server.\n" +
                "   |-->\n" +
                "  <servers>\n" +
                "    <!-- server\n" +
                "     | Specifies the authentication information to use when connecting to a particular server, identified by\n" +
                "     | a unique name within the system (referred to by the 'id' attribute below).\n" +
                "     |\n" +
                "     | NOTE: You should either specify username/password OR privateKey/passphrase, since these pairings are\n" +
                "     |       used together.\n" +
                "     |\n" +
                "    <server>\n" +
                "      <id>deploymentRepo</id>\n" +
                "      <username>repouser</username>\n" +
                "      <password>repopwd</password>\n" +
                "    </server>\n" +
                "    -->\n" +
                "\n" +
                "    <!-- Another sample, using keys to authenticate.\n" +
                "    <server>\n" +
                "      <id>siteServer</id>\n" +
                "      <privateKey>/path/to/private/key</privateKey>\n" +
                "      <passphrase>optional; leave empty if not used.</passphrase>\n" +
                "    </server>\n" +
                "    -->\n" +
                "  </servers>\n" +
                "\n" +
                "  <!-- mirrors\n" +
                "   | This is a list of mirrors to be used in downloading artifacts from remote repositories.\n" +
                "   |\n" +
                "   | It works like this: a POM may declare a repository to use in resolving certain artifacts.\n" +
                "   | However, this repository may have problems with heavy traffic at times, so people have mirrored\n" +
                "   | it to several places.\n" +
                "   |\n" +
                "   | That repository definition will have a unique id, so we can create a mirror reference for that\n" +
                "   | repository, to be used as an alternate download site. The mirror site will be the preferred\n" +
                "   | server for that repository.\n" +
                "   |-->\n" +
                "  <mirrors>\n" +
                "    <!-- mirror\n" +
                "     | Specifies a repository mirror site to use instead of a given repository. The repository that\n" +
                "     | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used\n" +
                "     | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.\n" +
                "     |\n" +
                "    <mirror>\n" +
                "      <id>mirrorId</id>\n" +
                "      <mirrorOf>repositoryId</mirrorOf>\n" +
                "      <name>Human Readable Name for this Mirror.</name>\n" +
                "      <url>http://my.repository.com/repo/path</url>\n" +
                "    </mirror>\n" +
                "     -->\n" +
                "    <mirror>\n" +
                "      <id>maven-default-http-blocker</id>\n" +
                "      <mirrorOf>external:http:*</mirrorOf>\n" +
                "      <name>Pseudo repository to mirror external repositories initially using HTTP.</name>\n" +
                "      <url>http://0.0.0.0/</url>\n" +
                "      <blocked>true</blocked>\n" +
                "    </mirror>\n" +
                "\t<!-- 阿里云仓库 -->\n" +
                "    <mirror>\n" +
                "       <id>alimaven</id>\n" +
                "          <mirrorOf>central</mirrorOf>\n" +
                "          <name>aliyun maven</name>\n" +
                "          <url>https://maven.aliyun.com/repository/central</url>\n" +
                "    </mirror>\n" +
                "  </mirrors>\n" +
                "  \n" +
                "\n" +
                "  <!-- profiles\n" +
                "   | This is a list of profiles which can be activated in a variety of ways, and which can modify\n" +
                "   | the build process. Profiles provided in the settings.xml are intended to provide local machine-\n" +
                "   | specific paths and repository locations which allow the build to work in the local environment.\n" +
                "   |\n" +
                "   | For example, if you have an integration testing plugin - like cactus - that needs to know where\n" +
                "   | your Tomcat instance is installed, you can provide a variable here such that the variable is\n" +
                "   | dereferenced during the build process to configure the cactus plugin.\n" +
                "   |\n" +
                "   | As noted above, profiles can be activated in a variety of ways. One way - the activeProfiles\n" +
                "   | section of this document (settings.xml) - will be discussed later. Another way essentially\n" +
                "   | relies on the detection of a system property, either matching a particular value for the property,\n" +
                "   | or merely testing its existence. Profiles can also be activated by JDK version prefix, where a\n" +
                "   | value of '1.4' might activate a profile when the build is executed on a JDK version of '1.4.2_07'.\n" +
                "   | Finally, the list of active profiles can be specified directly from the command line.\n" +
                "   |\n" +
                "   | NOTE: For profiles defined in the settings.xml, you are restricted to specifying only artifact\n" +
                "   |       repositories, plugin repositories, and free-form properties to be used as configuration\n" +
                "   |       variables for plugins in the POM.\n" +
                "   |\n" +
                "   |-->\n" +
                "  <profiles>\n" +
                "  <!-- java版本 --> \n" +
                "  <profile>\n" +
                "      <id>jdk-1.8</id>\n" +
                "      <activation>\n" +
                "\t    <activeByDefault>true</activeByDefault>\n" +
                "        <jdk>1.8</jdk>\n" +
                "      </activation>\n" +
                "\n" +
                "      <properties>\n" +
                "        <maven.compiler.source>1.8</maven.compiler.source>\n" +
                "\t\t<maven.compiler.target>1.8</maven.compiler.target>\n" +
                "\t\t<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>\n" +
                "      </properties>\n" +
                "  </profile>\n" +
                "    <!-- profile\n" +
                "     | Specifies a set of introductions to the build process, to be activated using one or more of the\n" +
                "     | mechanisms described above. For inheritance purposes, and to activate profiles via <activatedProfiles/>\n" +
                "     | or the command line, profiles have to have an ID that is unique.\n" +
                "     |\n" +
                "     | An encouraged best practice for profile identification is to use a consistent naming convention\n" +
                "     | for profiles, such as 'env-dev', 'env-test', 'env-production', 'user-jdcasey', 'user-brett', etc.\n" +
                "     | This will make it more intuitive to understand what the set of introduced profiles is attempting\n" +
                "     | to accomplish, particularly when you only have a list of profile id's for debug.\n" +
                "     |\n" +
                "     | This profile example uses the JDK version to trigger activation, and provides a JDK-specific repo.\n" +
                "    <profile>\n" +
                "      <id>jdk-1.4</id>\n" +
                "\n" +
                "      <activation>\n" +
                "        <jdk>1.4</jdk>\n" +
                "      </activation>\n" +
                "\n" +
                "      <repositories>\n" +
                "        <repository>\n" +
                "          <id>jdk14</id>\n" +
                "          <name>Repository for JDK 1.4 builds</name>\n" +
                "          <url>http://www.myhost.com/maven/jdk14</url>\n" +
                "          <layout>default</layout>\n" +
                "          <snapshotPolicy>always</snapshotPolicy>\n" +
                "        </repository>\n" +
                "      </repositories>\n" +
                "    </profile>\n" +
                "    -->\n" +
                "\n" +
                "    <!--\n" +
                "     | Here is another profile, activated by the system property 'target-env' with a value of 'dev',\n" +
                "     | which provides a specific path to the Tomcat instance. To use this, your plugin configuration\n" +
                "     | might hypothetically look like:\n" +
                "     |\n" +
                "     | ...\n" +
                "     | <plugin>\n" +
                "     |   <groupId>org.myco.myplugins</groupId>\n" +
                "     |   <artifactId>myplugin</artifactId>\n" +
                "     |\n" +
                "     |   <configuration>\n" +
                "     |     <tomcatLocation>${tomcatPath}</tomcatLocation>\n" +
                "     |   </configuration>\n" +
                "     | </plugin>\n" +
                "     | ...\n" +
                "     |\n" +
                "     | NOTE: If you just wanted to inject this configuration whenever someone set 'target-env' to\n" +
                "     |       anything, you could just leave off the <value/> inside the activation-property.\n" +
                "     |\n" +
                "    <profile>\n" +
                "      <id>env-dev</id>\n" +
                "\n" +
                "      <activation>\n" +
                "        <property>\n" +
                "          <name>target-env</name>\n" +
                "          <value>dev</value>\n" +
                "        </property>\n" +
                "      </activation>\n" +
                "\n" +
                "      <properties>\n" +
                "        <tomcatPath>/path/to/tomcat/instance</tomcatPath>\n" +
                "      </properties>\n" +
                "    </profile>\n" +
                "    -->\n" +
                "  </profiles>\n" +
                "\n" +
                "  <!-- activeProfiles\n" +
                "   | List of profiles that are active for all builds.\n" +
                "   |\n" +
                "  <activeProfiles>\n" +
                "    <activeProfile>alwaysActiveProfile</activeProfile>\n" +
                "    <activeProfile>anotherAlwaysActiveProfile</activeProfile>\n" +
                "  </activeProfiles>\n" +
                "  -->\n" +
                "</settings>\n";
        GzipCompress gzipCompress=new GzipCompress();
        SnappyCompress snappyCompress=new SnappyCompress();
        byte[] gzipByte = gzipCompress.compress(s.getBytes());
        byte[] snaByte = snappyCompress.compress(s.getBytes());
        System.out.println("s:"+s.getBytes().length+" gzip:"+gzipByte.length);
        System.out.println("s:"+s.getBytes().length+" snappy:"+snaByte.length);

    }
}
