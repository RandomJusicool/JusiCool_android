package com.jusiCool.data.local.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.jusiCool.data.AuthToken
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class AuthTokenSerializer @Inject constructor() : Serializer<AuthToken> {
    override val defaultValue: AuthToken = AuthToken.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AuthToken =
        try {
            AuthToken.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }

    override suspend fun writeTo(t: AuthToken, output: OutputStream) {
        t.writeTo(output)
    }
}