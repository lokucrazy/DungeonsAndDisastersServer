package com.mudndcapstone.server.utils

import com.mudndcapstone.server.models.Session
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class AuditorTests {

    @Test
    void givenNode_whenEnableAuditing_thenAuditingPropertiesAdded() {
        // Given
        Session session = new Session()

        // When
        Auditor.enableAuditing(session)

        // Then
        assert session.createdOn
        assert session.lastModifiedOn
    }

}
