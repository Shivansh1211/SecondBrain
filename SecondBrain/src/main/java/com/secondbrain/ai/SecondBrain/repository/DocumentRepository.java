package com.secondbrain.ai.SecondBrain.repository;

import com.secondbrain.ai.SecondBrain.entity.PdfDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<PdfDocument, Long> {
    }

