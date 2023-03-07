package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootTest(classes = [DemoApplicationTests.Konfig::class])
class DemoApplicationTests {

	@Configuration
	@EnableConfigurationProperties
	class Konfig {
		@Bean
		@ConfigurationProperties("event-hubs.producer")
		fun eventHubProducerProperties() = EventHubProducerProperties()

		@Bean
		fun someService() = SomeService(eventHubProducerProperties())
	}

	@Test
	fun contextLoads() {
	}

}

data class EventHubProducerProperties(var eventHubName: String? = null)

class SomeService(props: EventHubProducerProperties) {
	init { requireNotNull(props.eventHubName) }
}

