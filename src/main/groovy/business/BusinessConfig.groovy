package business

import org.aeonbits.owner.Config
import org.aeonbits.owner.ConfigFactory
import org.aeonbits.owner.crypto.AbstractDecryptor
import util.CryptoUtil
import org.aeonbits.owner.Config.DecryptorClass
import org.aeonbits.owner.Config.Sources

@DecryptorClass(Decryptor.class)
@Sources('classpath:${env}/business.properties')
interface BusinessConfig extends Config {

    @Config.Key('registered.admin.id')
    String registeredAdminId()

    @Config.Key('registered.user.id')
    String registeredUserId()

    @Config.Key('registered.admin.email')
    String registeredAdminEmail()

    @Config.Key('registered.user.email')
    String registeredUserEmail()

    @Config.EncryptedValue
    @Config.Key('registered.admin.password')
    String registeredAdminPassword()

    @Config.EncryptedValue
    @Config.Key('registered.user.password')
    String registeredUserPassword();

    BusinessConfig config = ConfigFactory.create BusinessConfig.class, System.getProperties(), System.getenv()

    final class Decryptor extends AbstractDecryptor {

        @Override
        String decrypt(String value) {
            return CryptoUtil.decrypt(value)
        }
    }
}
