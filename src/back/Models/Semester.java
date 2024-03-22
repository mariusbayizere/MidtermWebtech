package back.Models;
// Generated Mar 22, 2024 3:54:49 PM by Hibernate Tools 3.5.0.Final

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Semester generated by hbm2java
 */
@Entity
@Table(name = "semester", catalog = "auca")
public class Semester implements java.io.Serializable {

	private String id;
	private Course course;
	private String name;
	private Date startDate;
	private Date endDate;
	private List<Studentregistration> studentregistrations;

	public Semester() {
	}

	public Semester(String id, Course course, String name, Date startDate, Date endDate) {
		this.id = id;
		this.course = course;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Semester(String id, Course course, String name, Date startDate, Date endDate,
			List<Studentregistration> studentregistrations) {
		this.id = id;
		this.course = course;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.studentregistrations = studentregistrations;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false, length = 10)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "startDate", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "endDate", nullable = false, length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "semester")
	public List<Studentregistration> getStudentregistrations() {
		return this.studentregistrations;
	}

	public void setStudentregistrations(List<Studentregistration> studentregistrations) {
		this.studentregistrations = studentregistrations;
	}

}
