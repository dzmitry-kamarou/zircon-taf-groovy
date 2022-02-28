package app

import org.aeonbits.owner.Config
import org.aeonbits.owner.ConfigFactory
import org.aeonbits.owner.Config.Sources

@Sources('classpath:${env}/zircon.properties')
interface ZirconConfig extends Config {

    @Config.Key('ui.uri')
    String uiUri()

    @Config.Key('api.uri')
    String apiUri()

    ZirconConfig config = ConfigFactory.create ZirconConfig.class, System.getProperties(), System.getenv()
}
