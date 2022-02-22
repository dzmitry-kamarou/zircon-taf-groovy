package util

import groovy.util.logging.Slf4j
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor

import java.util.regex.Pattern

@Slf4j
class CryptoUtil {

    private static final def PD = System.getenv 'pd'
    private static final def STRING_OUTPUT_TYPE = 'base64'
    private static final def ENCRYPTOR = new StandardPBEStringEncryptor();
    private static final def ALGORITHM = 'PBEWITHMD5ANDDES'
    private static final def ENCRYPTED_PATTERN = Pattern.compile '^ENC\\((.+)\\)$'
    private static def isInitialized = false

    private static void init() {
        ENCRYPTOR.setAlgorithm ALGORITHM
        ENCRYPTOR.setPassword PD
        ENCRYPTOR.setStringOutputType STRING_OUTPUT_TYPE
        isInitialized = true
    }

    static void main(String[] args) {
        if (args == null || args.length != 1) {
            log.info 'Please pass exactly one parameter - string that require encryption.'
            System.exit 1
        }
        def encrypted = encrypt(args[0])
        log.info "Encrypted string: ${encrypted}"
        def decrypted = decrypt String.format("ENC(%s)", encrypted)
        log.info "Decrypted string: ${decrypted}"
        log.info "To add this encrypted value to .properties file use: ENC(${encrypted})"
    }

    private static String encrypt(String plain) {
        if (!isInitialized) {
            init()
        }
        return ENCRYPTOR.encrypt(plain)
    }

    static String decrypt(String encrypted) {
        if (!isInitialized) {
            init()
        }
        def matcher = ENCRYPTED_PATTERN.matcher encrypted
        if (matcher.matches()) {
            return ENCRYPTOR.decrypt(matcher.group(1))
        } else {
            throw new NotMatchedEncryptionPatternException()
        }
    }

    private static class NotMatchedEncryptionPatternException extends RuntimeException {

        NotMatchedEncryptionPatternException() {
            super('Encrypted string doesn\'t match the encryption pattern')
        }
    }
}
