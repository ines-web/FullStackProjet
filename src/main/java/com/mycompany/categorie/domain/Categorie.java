package com.mycompany.categorie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Categorie.
 */
@Entity
@Table(name = "categorie")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "pidParent", "categories" }, allowSetters = true)
    private Categorie pidParent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pidParent")
    @JsonIgnoreProperties(value = { "pidParent", "categories" }, allowSetters = true)
    private Set<Categorie> categories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Categorie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Categorie nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getPidParent() {
        return this.pidParent;
    }

    public void setPidParent(Categorie categorie) {
        this.pidParent = categorie;
    }

    public Categorie pidParent(Categorie categorie) {
        this.setPidParent(categorie);
        return this;
    }

    public Set<Categorie> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Categorie> categories) {
        if (this.categories != null) {
            this.categories.forEach(i -> i.setPidParent(null));
        }
        if (categories != null) {
            categories.forEach(i -> i.setPidParent(this));
        }
        this.categories = categories;
    }

    public Categorie categories(Set<Categorie> categories) {
        this.setCategories(categories);
        return this;
    }

    public Categorie addCategorie(Categorie categorie) {
        this.categories.add(categorie);
        categorie.setPidParent(this);
        return this;
    }

    public Categorie removeCategorie(Categorie categorie) {
        this.categories.remove(categorie);
        categorie.setPidParent(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categorie)) {
            return false;
        }
        return getId() != null && getId().equals(((Categorie) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Categorie{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
