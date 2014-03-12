package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Category implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private String name;

    protected Category() {}

//    @ElementCollection(fetch = FetchType.EAGER)
//    @MapKeyColumn(name = "locale")
//    @Column(name = "name")
//    @CollectionTable(name = "CategoryName", joinColumns = @JoinColumn(name = "category_id"))
//    private Map<Locale, String> langMap = new HashMap<>();

    public Category(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
