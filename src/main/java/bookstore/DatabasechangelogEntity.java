package bookstore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DATABASECHANGELOG", schema = "book-store", catalog = "")
public class DatabasechangelogEntity {
    @Basic
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "AUTHOR")
    private String author;
    @Basic
    @Column(name = "FILENAME")
    private String filename;
    @Basic
    @Column(name = "DATEEXECUTED")
    private Timestamp dateexecuted;
    @Basic
    @Column(name = "ORDEREXECUTED")
    private int orderexecuted;
    @Basic
    @Column(name = "EXECTYPE")
    private String exectype;
    @Basic
    @Column(name = "MD5SUM")
    private String md5Sum;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "COMMENTS")
    private String comments;
    @Basic
    @Column(name = "TAG")
    private String tag;
    @Basic
    @Column(name = "LIQUIBASE")
    private String liquibase;
    @Basic
    @Column(name = "CONTEXTS")
    private String contexts;
    @Basic
    @Column(name = "LABELS")
    private String labels;
    @Basic
    @Column(name = "DEPLOYMENT_ID")
    private String deploymentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Timestamp getDateexecuted() {
        return dateexecuted;
    }

    public void setDateexecuted(Timestamp dateexecuted) {
        this.dateexecuted = dateexecuted;
    }

    public int getOrderexecuted() {
        return orderexecuted;
    }

    public void setOrderexecuted(int orderexecuted) {
        this.orderexecuted = orderexecuted;
    }

    public String getExectype() {
        return exectype;
    }

    public void setExectype(String exectype) {
        this.exectype = exectype;
    }

    public String getMd5Sum() {
        return md5Sum;
    }

    public void setMd5Sum(String md5Sum) {
        this.md5Sum = md5Sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLiquibase() {
        return liquibase;
    }

    public void setLiquibase(String liquibase) {
        this.liquibase = liquibase;
    }

    public String getContexts() {
        return contexts;
    }

    public void setContexts(String contexts) {
        this.contexts = contexts;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabasechangelogEntity that = (DatabasechangelogEntity) o;
        return orderexecuted == that.orderexecuted && Objects.equals(id, that.id) && Objects.equals(author, that.author) && Objects.equals(filename, that.filename) && Objects.equals(dateexecuted, that.dateexecuted) && Objects.equals(exectype, that.exectype) && Objects.equals(md5Sum, that.md5Sum) && Objects.equals(description, that.description) && Objects.equals(comments, that.comments) && Objects.equals(tag, that.tag) && Objects.equals(liquibase, that.liquibase) && Objects.equals(contexts, that.contexts) && Objects.equals(labels, that.labels) && Objects.equals(deploymentId, that.deploymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, filename, dateexecuted, orderexecuted, exectype, md5Sum, description, comments, tag, liquibase, contexts, labels, deploymentId);
    }
}
