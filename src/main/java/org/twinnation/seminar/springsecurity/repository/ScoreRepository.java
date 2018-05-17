package org.twinnation.seminar.springsecurity.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.twinnation.seminar.springsecurity.bean.Score;


@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {



}
