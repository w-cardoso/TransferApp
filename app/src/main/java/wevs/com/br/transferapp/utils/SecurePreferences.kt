package wevs.com.br.transferapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import java.io.UnsupportedEncodingException
import java.security.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class SecurePreferences
/**
 * This will initialize an instance of the wevs.com.br.transferapp.utils.SecurePreferences class
 * @param context your current context.
 * @param preferenceName name of preferences file (preferenceName.xml)
 * @param secureKey the key used for encryption, finding a good key scheme is hard.
 * Hardcoding your key in the application is bad, but better than plaintext preferences. Having the user enter the key upon application launch is a safe(r) alternative, but annoying to the user.
 * @param encryptKeys settings this to false will only encrypt the values,
 * true will encrypt both values and keys. Keys can contain a lot of information about
 * the plaintext value of the value which can be used to decipher the value.
 * @throws SecurePreferencesException
 */
@Throws(SecurePreferencesException::class)
constructor(context: Context, preferenceName: String, secureKey: String, encryptKeys: Boolean) {

    private val encryptKeys: Boolean
    private val writer: Cipher
    private val reader: Cipher
    private val keyWriter: Cipher
    private val preferences: SharedPreferences

    private val iv: IvParameterSpec
        get() {
            val iv = ByteArray(writer.blockSize)
            System.arraycopy(
                "fldsjfodasjifudslfjdsaofshaufihadsf".toByteArray(),
                0,
                iv,
                0,
                writer.blockSize
            )
            return IvParameterSpec(iv)
        }

    class SecurePreferencesException(e: Throwable) : RuntimeException(e)

    init {
        try {
            this.writer = Cipher.getInstance(TRANSFORMATION)
            this.reader = Cipher.getInstance(TRANSFORMATION)
            this.keyWriter = Cipher.getInstance(KEY_TRANSFORMATION)

            initCiphers(secureKey)

            this.preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

            this.encryptKeys = encryptKeys
        } catch (e: GeneralSecurityException) {
            throw SecurePreferencesException(
                e
            )
        } catch (e: UnsupportedEncodingException) {
            throw SecurePreferencesException(
                e
            )
        }

    }

    @Throws(
        UnsupportedEncodingException::class,
        NoSuchAlgorithmException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class
    )
    protected fun initCiphers(secureKey: String) {
        val ivSpec = iv
        val secretKey = getSecretKey(secureKey)

        writer.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
        reader.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
        keyWriter.init(Cipher.ENCRYPT_MODE, secretKey)
    }

    @Throws(UnsupportedEncodingException::class, NoSuchAlgorithmException::class)
    protected fun getSecretKey(key: String): SecretKeySpec {
        val keyBytes = createKeyBytes(key)
        return SecretKeySpec(keyBytes,
            TRANSFORMATION
        )
    }

    @Throws(UnsupportedEncodingException::class, NoSuchAlgorithmException::class)
    protected fun createKeyBytes(key: String): ByteArray {
        val md = MessageDigest.getInstance(SECRET_KEY_HASH_TRANSFORMATION)
        md.reset()
        return md.digest(key.toByteArray(charset(CHARSET)))
    }

    fun put(key: String, value: String?) {
        if (value == null) {
            preferences.edit().remove(toKey(key)).apply()
        } else {
            putValue(toKey(key), value)
        }
    }

    fun containsKey(key: String): Boolean {
        return preferences.contains(toKey(key))
    }

    fun removeValue(key: String) {
        preferences.edit().remove(toKey(key)).apply()
    }

    @Throws(SecurePreferencesException::class)
    fun getString(key: String): String? {
        if (preferences.contains(toKey(key))) {
            val securedEncodedValue = preferences.getString(toKey(key), "")
            return decrypt(securedEncodedValue)
        }
        return null
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    private fun toKey(key: String): String {
        return if (encryptKeys)
            encrypt(key, keyWriter)
        else
            key
    }

    @Throws(SecurePreferencesException::class)
    private fun putValue(key: String, value: String) {
        val secureValueEncoded = encrypt(value, writer)
        preferences.edit().putString(key, secureValueEncoded).apply()
    }

    @Throws(SecurePreferencesException::class)
    protected fun encrypt(value: String, writer: Cipher): String {

        val secureValue: ByteArray
        try {
            secureValue =
                convert(
                    writer,
                    value.toByteArray(charset(CHARSET))
                )
        } catch (e: UnsupportedEncodingException) {
            throw SecurePreferencesException(
                e
            )
        }

        return Base64.encodeToString(secureValue, Base64.NO_WRAP)
    }

    protected fun decrypt(securedEncodedValue: String?): String {
        val securedValue = Base64.decode(securedEncodedValue, Base64.NO_WRAP)
        val value =
            convert(
                reader,
                securedValue
            )
        try {
            return String(value, charset(CHARSET))
        } catch (e: UnsupportedEncodingException) {
            throw SecurePreferencesException(
                e
            )
        }

    }


    companion object {

        private val TRANSFORMATION = "AES/CBC/PKCS5Padding"
        private val KEY_TRANSFORMATION = "AES/ECB/PKCS5Padding"
        private val SECRET_KEY_HASH_TRANSFORMATION = "SHA-256"
        private val CHARSET = "UTF-8"

        @Throws(SecurePreferencesException::class)
        private fun convert(cipher: Cipher, bs: ByteArray): ByteArray {
            try {
                return cipher.doFinal(bs)
            } catch (e: Exception) {
                throw SecurePreferencesException(
                    e
                )
            }

        }
    }
}