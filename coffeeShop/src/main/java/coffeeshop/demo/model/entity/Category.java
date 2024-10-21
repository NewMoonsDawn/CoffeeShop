package coffeeshop.demo.model.entity;


import javax.persistence.*;


@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private CategoryName name;
    private Integer neededTime;

    public Category() {
    }

    @Enumerated(EnumType.STRING)
    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName category) {
        this.name = category;
    }

    @Column(nullable = false)
    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }
}
