package io.tacsio.udemy.rabbitmq.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class DummyMessage(val content: String, val publishOrder: Int)