package com.dictionary.repo;

import com.dictionary.model.entity.Language;
import com.dictionary.model.enums.LanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {
    Language findByLanguageName(LanguageName name);
}