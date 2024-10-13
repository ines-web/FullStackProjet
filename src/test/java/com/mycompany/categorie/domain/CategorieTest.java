package com.mycompany.categorie.domain;

import static com.mycompany.categorie.domain.CategorieTestSamples.*;
import static com.mycompany.categorie.domain.CategorieTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.categorie.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CategorieTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Categorie.class);
        Categorie categorie1 = getCategorieSample1();
        Categorie categorie2 = new Categorie();
        assertThat(categorie1).isNotEqualTo(categorie2);

        categorie2.setId(categorie1.getId());
        assertThat(categorie1).isEqualTo(categorie2);

        categorie2 = getCategorieSample2();
        assertThat(categorie1).isNotEqualTo(categorie2);
    }

    @Test
    void pidParentTest() {
        Categorie categorie = getCategorieRandomSampleGenerator();
        Categorie categorieBack = getCategorieRandomSampleGenerator();

        categorie.setPidParent(categorieBack);
        assertThat(categorie.getPidParent()).isEqualTo(categorieBack);

        categorie.pidParent(null);
        assertThat(categorie.getPidParent()).isNull();
    }

    @Test
    void categorieTest() {
        Categorie categorie = getCategorieRandomSampleGenerator();
        Categorie categorieBack = getCategorieRandomSampleGenerator();

        categorie.addCategorie(categorieBack);
        assertThat(categorie.getCategories()).containsOnly(categorieBack);
        assertThat(categorieBack.getPidParent()).isEqualTo(categorie);

        categorie.removeCategorie(categorieBack);
        assertThat(categorie.getCategories()).doesNotContain(categorieBack);
        assertThat(categorieBack.getPidParent()).isNull();

        categorie.categories(new HashSet<>(Set.of(categorieBack)));
        assertThat(categorie.getCategories()).containsOnly(categorieBack);
        assertThat(categorieBack.getPidParent()).isEqualTo(categorie);

        categorie.setCategories(new HashSet<>());
        assertThat(categorie.getCategories()).doesNotContain(categorieBack);
        assertThat(categorieBack.getPidParent()).isNull();
    }
}
