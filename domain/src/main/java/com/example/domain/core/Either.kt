package com.example.domain.core

/**
 * Created by jsmirabal on 4/19/2019.
 */
sealed class Either<out L, out R> {

    class Left<A>(val value: A): Either<A, Nothing>()
    class Right<B>(val value: B): Either<Nothing, B>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun either(fnL: (L) -> Unit, fnR: (R) -> Unit): Unit =
        when (this) {
            is Left -> fnL(value)
            is Right -> fnR(value)
        }

    override fun equals(other: Any?): Boolean {
        return this.toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode() * 123
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun toString(): String {
        return "${this.javaClass.simpleName}(value=${if (this is Left) this.value else if (this is Right) this.value else "undetermined"})"
    }
}