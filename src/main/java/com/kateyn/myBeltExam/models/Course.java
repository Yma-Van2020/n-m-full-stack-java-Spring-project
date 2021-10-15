package com.kateyn.myBeltExam.models;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
 // ========== Member Variables ==============
    @NotNull
    @Size(min = 2, max = 200, message="Name must be at least 2 chars")
    private String name;
    
    @NotNull
    @Size(min = 3, max = 20, message="Day of week must be at least 3 chars")
    private String dayOfWeek;
    
    @NotNull
    @Min(0)
    private float price;
    
    @NotNull
    @Size(min = 5, max = 200, message="Description must be at least 5 chars")
    private String description;
    
    @NotNull
    private String time;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User instructor;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
	    	name="courses_students",
	    	joinColumns = @JoinColumn(name="course_id"),
	    	inverseJoinColumns = @JoinColumn(name= "student_id")
	    )
	    private List<Student> students;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Course() {}

	public Course(@NotNull @Size(min = 2, max = 200, message = "Name must be at least 2 chars") String name,
			@NotNull String dayOfWeek, @NotNull @Min(0) float price,
			@NotNull @Size(min = 5, max = 200, message = "Description must be at least 5 chars") String description,
			@NotNull User instructor) {
		this.name = name;
		this.dayOfWeek = dayOfWeek;
		this.price = price;
		this.description = description;
		this.instructor = instructor;
	};
    
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getInstructor() {
		return instructor;
	}

	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}