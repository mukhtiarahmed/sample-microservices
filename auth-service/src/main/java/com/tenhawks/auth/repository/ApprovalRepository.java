package com.tenhawks.auth.repository;

import com.tenhawks.auth.domain.Approval;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;
/**
 * @author Mukhtiar Ahmed
 */
public interface ApprovalRepository extends CassandraRepository<Approval, UUID> {
}
