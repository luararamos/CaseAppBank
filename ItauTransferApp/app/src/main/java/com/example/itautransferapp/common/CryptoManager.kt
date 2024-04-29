package com.example.itautransferapp.common

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object CryptoManager {
    private const val KEY_ALIAS = "myAppKeyAlias"
    private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
    private const val TRANSFORMATION = "AES/GCM/NoPadding"
    private const val TAG_LENGTH_BIT = 128
    private const val IV_SIZE = 12

    fun getSecretKey(): SecretKey {
        val keyStore = java.security.KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)

        keyStore.getKey(KEY_ALIAS, null)?.let {
            return it as SecretKey
        }

        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE_PROVIDER)
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            setKeySize(256)
            setRandomizedEncryptionRequired(true)
            setUserAuthenticationRequired(false)
            setRandomizedEncryptionRequired(true) // important for GCM
        }.build()

        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    fun encrypt(data: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val secretKey = getSecretKey()
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encryption = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return iv.plus(encryption).toBase64()
    }

    fun decrypt(encryptedData: String): String {
        val data = encryptedData.fromBase64()
        val iv = data.copyOfRange(0, IV_SIZE)
        val encrypted = data.copyOfRange(IV_SIZE, data.size)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        val secretKey = getSecretKey()
        val spec = GCMParameterSpec(TAG_LENGTH_BIT, iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

        return String(cipher.doFinal(encrypted), Charsets.UTF_8)
    }

    private fun ByteArray.toBase64(): String = android.util.Base64.encodeToString(this, android.util.Base64.DEFAULT)
    private fun String.fromBase64(): ByteArray = android.util.Base64.decode(this, android.util.Base64.DEFAULT)
}
