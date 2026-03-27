package com.secondbrain.ai.SecondBrain.repository;

import com.secondbrain.ai.SecondBrain.entity.PdfDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfDocumentRepository extends JpaRepository<PdfDocument, Long> {
    List<PdfDocument> findAllByUserId(Long userId);
    }

