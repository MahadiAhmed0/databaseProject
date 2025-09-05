import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    // Header controls
    @FXML private Label adminNameLabel;
    @FXML private Button logoutButton;

    // Navigation buttons
    @FXML private Button dashboardBtn;
    @FXML private Button usersBtn;
    @FXML private Button coursesBtn;
    @FXML private Button semestersBtn;
    @FXML private Button enrollmentsBtn;
    @FXML private Button assessmentsBtn;
    @FXML private Button reportsBtn;

    // Main content area
    @FXML private StackPane contentArea;

    // Dashboard view controls
    @FXML private VBox dashboardView;
    @FXML private Label totalStudentsLabel;
    @FXML private Label totalFacultyLabel;
    @FXML private Label activeCoursesLabel;
    @FXML private Label totalEnrollmentsLabel;
    @FXML private TableView<Activity> recentActivitiesTable;
    @FXML private TableColumn<Activity, String> activityTimeCol;
    @FXML private TableColumn<Activity, String> activityTypeCol;
    @FXML private TableColumn<Activity, String> activityDescCol;
    @FXML private TableColumn<Activity, String> activityUserCol;

    // Users view controls
    @FXML private VBox usersView;
    @FXML private Button addUserBtn;
    @FXML private ComboBox<String> userRoleFilter;
    @FXML private TextField userSearchField;
    @FXML private Button searchUserBtn;
    @FXML private Button refreshUsersBtn;
    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, String> userIdCol;
    @FXML private TableColumn<User, String> userNameCol;
    @FXML private TableColumn<User, String> userRoleCol;
    @FXML private TableColumn<User, String> userEmailCol;
    @FXML private TableColumn<User, String> userPhoneCol;
    @FXML private TableColumn<User, String> userStatusCol;
    @FXML private TableColumn<User, String> userJoinDateCol;
    @FXML private TableColumn<User, Void> userActionsCol;

    // Courses view controls
    @FXML private VBox coursesView;
    @FXML private Button addCourseBtn;
    @FXML private ComboBox<String> courseSemesterFilter;
    @FXML private TextField courseSearchField;
    @FXML private Button searchCourseBtn;
    @FXML private Button refreshCoursesBtn;
    @FXML private TableView<Course> coursesTable;
    @FXML private TableColumn<Course, Integer> courseIdCol;
    @FXML private TableColumn<Course, String> courseCodeCol;
    @FXML private TableColumn<Course, String> courseNameCol;
    @FXML private TableColumn<Course, Double> courseCreditCol;
    @FXML private TableColumn<Course, Integer> courseSemesterCol;
    @FXML private TableColumn<Course, Void> courseActionsCol;

    // Semesters view controls
    @FXML private VBox semestersView;
    @FXML private Button addSemesterBtn;
    @FXML private TableView<Semester> semestersTable;
    @FXML private TableColumn<Semester, Integer> semesterIdCol;
    @FXML private TableColumn<Semester, String> semesterTermCol;
    @FXML private TableColumn<Semester, String> semesterStartDateCol;
    @FXML private TableColumn<Semester, Void> semesterActionsCol;

    // Enrollments view controls
    @FXML private VBox enrollmentsView;
    @FXML private Button addEnrollmentBtn;
    @FXML private ComboBox<String> enrollmentSemesterFilter;
    @FXML private TextField enrollmentSearchField;
    @FXML private Button searchEnrollmentBtn;
    @FXML private Button refreshEnrollmentsBtn;
    @FXML private TableView<Enrollment> enrollmentsTable;
    @FXML private TableColumn<Enrollment, Integer> enrollmentIdCol;
    @FXML private TableColumn<Enrollment, String> enrollmentStudentCol;
    @FXML private TableColumn<Enrollment, String> enrollmentCourseCol;
    @FXML private TableColumn<Enrollment, String> enrollmentSemesterTermCol;
    @FXML private TableColumn<Enrollment, String> enrollmentFacultyCol;
    @FXML private TableColumn<Enrollment, Void> enrollmentActionsCol;

    // Assessments view controls
    @FXML private VBox assessmentsView;
    @FXML private Button addAssessmentTypeBtn;
    @FXML private Button addCourseAssessmentBtn;
    @FXML private TableView<AssessmentType> assessmentTypesTable;
    @FXML private TableColumn<AssessmentType, Integer> assessmentTypeIdCol;
    @FXML private TableColumn<AssessmentType, String> assessmentTypeNameCol;
    @FXML private TableColumn<AssessmentType, Double> assessmentTypePercentageCol;
    @FXML private TableColumn<AssessmentType, Void> assessmentTypeActionsCol;
    @FXML private TableView<CourseAssessment> courseAssessmentsTable;
    @FXML private TableColumn<CourseAssessment, Integer> courseAssessmentIdCol;
    @FXML private TableColumn<CourseAssessment, String> courseAssessmentOfferingCol;
    @FXML private TableColumn<CourseAssessment, String> courseAssessmentTypeCol;
    @FXML private TableColumn<CourseAssessment, String> courseAssessmentNameCol;
    @FXML private TableColumn<CourseAssessment, Double> courseAssessmentMarksCol;
    @FXML private TableColumn<CourseAssessment, Void> courseAssessmentActionsCol;

    // Reports view controls
    @FXML private VBox reportsView;
    @FXML private Button studentListReportBtn;
    @FXML private Button enrollmentReportBtn;
    @FXML private Button performanceReportBtn;
    @FXML private Button courseListReportBtn;
    @FXML private Button courseOfferingReportBtn;
    @FXML private Button facultyWorkloadReportBtn;
    @FXML private Button userActivityReportBtn;
    @FXML private Button systemStatsReportBtn;
    @FXML private Button alertsReportBtn;
    @FXML private TextArea reportPreviewArea;
    @FXML private Button exportPdfBtn;
    @FXML private Button exportExcelBtn;
    @FXML private Button printReportBtn;

    // Data collections
    private ObservableList<User> usersList = FXCollections.observableArrayList();
    private ObservableList<Course> coursesList = FXCollections.observableArrayList();
    private ObservableList<Semester> semestersList = FXCollections.observableArrayList();
    private ObservableList<Enrollment> enrollmentsList = FXCollections.observableArrayList();
    private ObservableList<AssessmentType> assessmentTypesList = FXCollections.observableArrayList();
    private ObservableList<CourseAssessment> courseAssessmentsList = FXCollections.observableArrayList();
    private ObservableList<Activity> activitiesList = FXCollections.observableArrayList();

    // Database connection
    private DatabaseManager dbManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbManager = new DatabaseManager();
        setupNavigationButtons();
        initializeTableColumns();
        loadInitialData();
        showDashboard();
    }

    private void setupNavigationButtons() {
        // Set navigation button styles
        dashboardBtn.getStyleClass().add("nav-btn");
        usersBtn.getStyleClass().add("nav-btn");
        coursesBtn.getStyleClass().add("nav-btn");
        semestersBtn.getStyleClass().add("nav-btn");
        enrollmentsBtn.getStyleClass().add("nav-btn");
        assessmentsBtn.getStyleClass().add("nav-btn");
        reportsBtn.getStyleClass().add("nav-btn");
    }

    private void initializeTableColumns() {
        // Initialize Users table columns
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        userRoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        userEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        userPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        userStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        userJoinDateCol.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        addUserActionsColumn();

        // Initialize Courses table columns
        courseIdCol.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCreditCol.setCellValueFactory(new PropertyValueFactory<>("credit"));
        courseSemesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        addCourseActionsColumn();

        // Initialize Semesters table columns
        semesterIdCol.setCellValueFactory(new PropertyValueFactory<>("semesterId"));
        semesterTermCol.setCellValueFactory(new PropertyValueFactory<>("term"));
        semesterStartDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        addSemesterActionsColumn();

        // Initialize Enrollments table columns
        enrollmentIdCol.setCellValueFactory(new PropertyValueFactory<>("enrollmentId"));
        enrollmentStudentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        enrollmentCourseCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        enrollmentSemesterTermCol.setCellValueFactory(new PropertyValueFactory<>("semesterTerm"));
        enrollmentFacultyCol.setCellValueFactory(new PropertyValueFactory<>("facultyName"));
        addEnrollmentActionsColumn();

        // Initialize Assessment Types table columns
        assessmentTypeIdCol.setCellValueFactory(new PropertyValueFactory<>("assessmentTypeId"));
        assessmentTypeNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assessmentTypePercentageCol.setCellValueFactory(new PropertyValueFactory<>("marksPercentage"));
        addAssessmentTypeActionsColumn();

        // Initialize Course Assessments table columns
        courseAssessmentIdCol.setCellValueFactory(new PropertyValueFactory<>("assessmentId"));
        courseAssessmentOfferingCol.setCellValueFactory(new PropertyValueFactory<>("courseOffering"));
        courseAssessmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("assessmentType"));
        courseAssessmentNameCol.setCellValueFactory(new PropertyValueFactory<>("assessmentName"));
        courseAssessmentMarksCol.setCellValueFactory(new PropertyValueFactory<>("totalMarks"));
        addCourseAssessmentActionsColumn();

        // Initialize Activities table columns
        activityTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        activityTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        activityDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        activityUserCol.setCellValueFactory(new PropertyValueFactory<>("user"));
    }

    private void addUserActionsColumn() {
        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {
                    private final Button editBtn = new Button("Edit");
                    private final Button deleteBtn = new Button("Delete");

                    {
                        editBtn.getStyleClass().add("secondary-btn");
                        deleteBtn.getStyleClass().add("danger-btn");

                        editBtn.setOnAction((ActionEvent event) -> {
                            User user = getTableView().getItems().get(getIndex());
                            editUser(user);
                        });

                        deleteBtn.setOnAction((ActionEvent event) -> {
                            User user = getTableView().getItems().get(getIndex());
                            deleteUser(user);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            var hbox = new javafx.scene.layout.HBox(5);
                            hbox.getChildren().addAll(editBtn, deleteBtn);
                            setGraphic(hbox);
                        }
                    }
                };
                return cell;
            }
        };
        userActionsCol.setCellFactory(cellFactory);
    }

    private void addCourseActionsColumn() {
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = new Callback<TableColumn<Course, Void>, TableCell<Course, Void>>() {
            @Override
            public TableCell<Course, Void> call(final TableColumn<Course, Void> param) {
                final TableCell<Course, Void> cell = new TableCell<Course, Void>() {
                    private final Button editBtn = new Button("Edit");
                    private final Button deleteBtn = new Button("Delete");

                    {
                        editBtn.getStyleClass().add("secondary-btn");
                        deleteBtn.getStyleClass().add("danger-btn");

                        editBtn.setOnAction((ActionEvent event) -> {
                            Course course = getTableView().getItems().get(getIndex());
                            editCourse(course);
                        });

                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Course course = getTableView().getItems().get(getIndex());
                            deleteCourse(course);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            var hbox = new javafx.scene.layout.HBox(5);
                            hbox.getChildren().addAll(editBtn, deleteBtn);
                            setGraphic(hbox);
                        }
                    }
                };
                return cell;
            }
        };
        courseActionsCol.setCellFactory(cellFactory);
    }

    private void addSemesterActionsColumn() {
        Callback<TableColumn<Semester, Void>, TableCell<Semester, Void>> cellFactory = new Callback<TableColumn<Semester, Void>, TableCell<Semester, Void>>() {
            @Override
            public TableCell<Semester, Void> call(final TableColumn<Semester, Void> param) {
                final TableCell<Semester, Void> cell = new TableCell<Semester, Void>() {
                    private final Button editBtn = new Button("Edit");
                    private final Button deleteBtn = new Button("Delete");

                    {
                        editBtn.getStyleClass().add("secondary-btn");
                        deleteBtn.getStyleClass().add("danger-btn");

                        editBtn.setOnAction((ActionEvent event) -> {
                            Semester semester = getTableView().getItems().get(getIndex());
                            editSemester(semester);
                        });

                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Semester semester = getTableView().getItems().get(getIndex());
                            deleteSemester(semester);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            var hbox = new javafx.scene.layout.HBox(5);
                            hbox.getChildren().addAll(editBtn, deleteBtn);
                            setGraphic(hbox);
                        }
                    }
                };
                return cell;
            }
        };
        semesterActionsCol.setCellFactory(cellFactory);
    }

    private void addEnrollmentActionsColumn() {
        Callback<TableColumn<Enrollment, Void>, TableCell<Enrollment, Void>> cellFactory = new Callback<TableColumn<Enrollment, Void>, TableCell<Enrollment, Void>>() {
            @Override
            public TableCell<Enrollment, Void> call(final TableColumn<Enrollment, Void> param) {
                final TableCell<Enrollment, Void> cell = new TableCell<Enrollment, Void>() {
                    private final Button removeBtn = new Button("Remove");

                    {
                        removeBtn.getStyleClass().add("danger-btn");

                        removeBtn.setOnAction((ActionEvent event) -> {
                            Enrollment enrollment = getTableView().getItems().get(getIndex());
                            removeEnrollment(enrollment);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(removeBtn);
                        }
                    }
                };
                return cell;
            }
        };
        enrollmentActionsCol.setCellFactory(cellFactory);
    }

    private void addAssessmentTypeActionsColumn() {
        Callback<TableColumn<AssessmentType, Void>, TableCell<AssessmentType, Void>> cellFactory = new Callback<TableColumn<AssessmentType, Void>, TableCell<AssessmentType, Void>>() {
            @Override
            public TableCell<AssessmentType, Void> call(final TableColumn<AssessmentType, Void> param) {
                final TableCell<AssessmentType, Void> cell = new TableCell<AssessmentType, Void>() {
                    private final Button editBtn = new Button("Edit");
                    private final Button deleteBtn = new Button("Delete");

                    {
                        editBtn.getStyleClass().add("secondary-btn");
                        deleteBtn.getStyleClass().add("danger-btn");

                        editBtn.setOnAction((ActionEvent event) -> {
                            AssessmentType type = getTableView().getItems().get(getIndex());
                            editAssessmentType(type);
                        });

                        deleteBtn.setOnAction((ActionEvent event) -> {
                            AssessmentType type = getTableView().getItems().get(getIndex());
                            deleteAssessmentType(type);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            var hbox = new javafx.scene.layout.HBox(5);
                            hbox.getChildren().addAll(editBtn, deleteBtn);
                            setGraphic(hbox);
                        }
                    }
                };
                return cell;
            }
        };
        assessmentTypeActionsCol.setCellFactory(cellFactory);
    }

    private void addCourseAssessmentActionsColumn() {
        Callback<TableColumn<CourseAssessment, Void>, TableCell<CourseAssessment, Void>> cellFactory = new Callback<TableColumn<CourseAssessment, Void>, TableCell<CourseAssessment, Void>>() {
            @Override
            public TableCell<CourseAssessment, Void> call(final TableColumn<CourseAssessment, Void> param) {
                final TableCell<CourseAssessment, Void> cell = new TableCell<CourseAssessment, Void>() {
                    private final Button editBtn = new Button("Edit");
                    private final Button deleteBtn = new Button("Delete");

                    {
                        editBtn.getStyleClass().add("secondary-btn");
                        deleteBtn.getStyleClass().add("danger-btn");

                        editBtn.setOnAction((ActionEvent event) -> {
                            CourseAssessment assessment = getTableView().getItems().get(getIndex());
                            editCourseAssessment(assessment);
                        });

                        deleteBtn.setOnAction((ActionEvent event) -> {
                            CourseAssessment assessment = getTableView().getItems().get(getIndex());
                            deleteCourseAssessment(assessment);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            var hbox = new javafx.scene.layout.HBox(5);
                            hbox.getChildren().addAll(editBtn, deleteBtn);
                            setGraphic(hbox);
                        }
                    }
                };
                return cell;
            }
        };
        courseAssessmentActionsCol.setCellFactory(cellFactory);
    }

    private void loadInitialData() {
        loadDashboardData();
        loadUsers();
        loadCourses();
        loadSemesters();
        loadEnrollments();
        loadAssessmentTypes();
        loadCourseAssessments();
        setupFilters();
    }

    private void loadDashboardData() {
        try {
            // Load statistics
            totalStudentsLabel.setText(String.valueOf(dbManager.getTotalStudents()));
            totalFacultyLabel.setText(String.valueOf(dbManager.getTotalFaculty()));
            activeCoursesLabel.setText(String.valueOf(dbManager.getActiveCourses()));
            totalEnrollmentsLabel.setText(String.valueOf(dbManager.getTotalEnrollments()));

            // Load recent activities
            activitiesList.setAll(dbManager.getRecentActivities());
            recentActivitiesTable.setItems(activitiesList);
        } catch (SQLException e) {
            showError("Error loading dashboard data", e.getMessage());
        }
    }

    private void loadUsers() {
        try {
            usersList.setAll(dbManager.getAllUsers());
            usersTable.setItems(usersList);
        } catch (SQLException e) {
            showError("Error loading users", e.getMessage());
        }
    }

    private void loadCourses() {
        try {
            coursesList.setAll(dbManager.getAllCourses());
            coursesTable.setItems(coursesList);
        } catch (SQLException e) {
            showError("Error loading courses", e.getMessage());
        }
    }

    private void loadSemesters() {
        try {
            semestersList.setAll(dbManager.getAllSemesters());
            semestersTable.setItems(semestersList);
        } catch (SQLException e) {
            showError("Error loading semesters", e.getMessage());
        }
    }

    private void loadEnrollments() {
        try {
            enrollmentsList.setAll(dbManager.getAllEnrollments());
            enrollmentsTable.setItems(enrollmentsList);
        } catch (SQLException e) {
            showError("Error loading enrollments", e.getMessage());
        }
    }

    private void loadAssessmentTypes() {
        try {
            assessmentTypesList.setAll(dbManager.getAllAssessmentTypes());
            assessmentTypesTable.setItems(assessmentTypesList);
        } catch (SQLException e) {
            showError("Error loading assessment types", e.getMessage());
        }
    }

    private void loadCourseAssessments() {
        try {
            courseAssessmentsList.setAll(dbManager.getAllCourseAssessments());
            courseAssessmentsTable.setItems(courseAssessmentsList);
        } catch (SQLException e) {
            showError("Error loading course assessments", e.getMessage());
        }
    }

    private void setupFilters() {
        // Setup user role filter
        userRoleFilter.getItems().addAll("All", "student", "faculty", "admin");
        userRoleFilter.setValue("All");
        userRoleFilter.setOnAction(e -> filterUsers());

        // Setup course semester filter
        courseSemesterFilter.getItems().clear();
        courseSemesterFilter.getItems().add("All Semesters");
        for (int i = 1; i <= 12; i++) {
            courseSemesterFilter.getItems().add("Semester " + i);
        }
        courseSemesterFilter.setValue("All Semesters");
        courseSemesterFilter.setOnAction(e -> filterCourses());

        // Setup enrollment semester filter
        enrollmentSemesterFilter.getItems().clear();
        enrollmentSemesterFilter.getItems().add("All Semesters");
        try {
            for (Semester semester : dbManager.getAllSemesters()) {
                enrollmentSemesterFilter.getItems().add(semester.getTerm() + " - " + semester.getStartDate());
            }
        } catch (SQLException e) {
            showError("Error loading semester filters", e.getMessage());
        }
        enrollmentSemesterFilter.setValue("All Semesters");
        enrollmentSemesterFilter.setOnAction(e -> filterEnrollments());
    }

    // Navigation methods
    @FXML
    private void showDashboard() {
        hideAllViews();
        dashboardView.setVisible(true);
        loadDashboardData();
        setActiveNavButton(dashboardBtn);
    }

    @FXML
    private void showUsers() {
        hideAllViews();
        usersView.setVisible(true);
        loadUsers();
        setActiveNavButton(usersBtn);
    }

    @FXML
    private void showCourses() {
        hideAllViews();
        coursesView.setVisible(true);
        loadCourses();
        setActiveNavButton(coursesBtn);
    }

    @FXML
    private void showSemesters() {
        hideAllViews();
        semestersView.setVisible(true);
        loadSemesters();
        setActiveNavButton(semestersBtn);
    }

    @FXML
    private void showEnrollments() {
        hideAllViews();
        enrollmentsView.setVisible(true);
        loadEnrollments();
        setActiveNavButton(enrollmentsBtn);
    }

    @FXML
    private void showAssessments() {
        hideAllViews();
        assessmentsView.setVisible(true);
        loadAssessmentTypes();
        loadCourseAssessments();
        setActiveNavButton(assessmentsBtn);
    }

    @FXML
    private void showReports() {
        hideAllViews();
        reportsView.setVisible(true);
        setActiveNavButton(reportsBtn);
    }

    private void hideAllViews() {
        dashboardView.setVisible(false);
        usersView.setVisible(false);
        coursesView.setVisible(false);
        semestersView.setVisible(false);
        enrollmentsView.setVisible(false);
        assessmentsView.setVisible(false);
        reportsView.setVisible(false);
    }

    private void setActiveNavButton(Button activeButton) {
        // Remove active class from all buttons
        dashboardBtn.getStyleClass().removeAll("active");
        usersBtn.getStyleClass().removeAll("active");
        coursesBtn.getStyleClass().removeAll("active");
        semestersBtn.getStyleClass().removeAll("active");
        enrollmentsBtn.getStyleClass().removeAll("active");
        assessmentsBtn.getStyleClass().removeAll("active");
        reportsBtn.getStyleClass().removeAll("active");

        // Add active class to the selected button
        activeButton.getStyleClass().add("active");
    }

    // User management methods
    @FXML
    private void showAddUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUserDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add New User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(usersBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            AddUserDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening add user dialog", e.getMessage());
        }
    }

    @FXML
    private void searchUsers() {
        filterUsers();
    }

    @FXML
    private void refreshUsers() {
        userSearchField.clear();
        userRoleFilter.setValue("All");
        loadUsers();
    }

    private void filterUsers() {
        try {
            String roleFilter = userRoleFilter.getValue();
            String searchText = userSearchField.getText().toLowerCase();

            ObservableList<User> filteredUsers = FXCollections.observableArrayList();

            for (User user : usersList) {
                boolean matchesRole = roleFilter.equals("All") || user.getRole().equals(roleFilter);
                boolean matchesSearch = searchText.isEmpty() ||
                        user.getName().toLowerCase().contains(searchText) ||
                        user.getEmail().toLowerCase().contains(searchText);

                if (matchesRole && matchesSearch) {
                    filteredUsers.add(user);
                }
            }

            usersTable.setItems(filteredUsers);
        } catch (Exception e) {
            showError("Error filtering users", e.getMessage());
        }
    }

    private void editUser(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditUserDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User - " + user.getName());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(usersBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            EditUserDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);
            controller.setUser(user);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening edit user dialog", e.getMessage());
        }
    }

    private void deleteUser(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User");
        alert.setHeaderText("Are you sure you want to delete this user?");
        alert.setContentText("User: " + user.getName() + " (" + user.getEmail() + ")\nThis action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                dbManager.deleteUser(user.getUserId());
                loadUsers();
                showSuccess("User deleted successfully");
            } catch (SQLException e) {
                showError("Error deleting user", e.getMessage());
            }
        }
    }

    @FXML
    private void editUser() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            editUser(selectedUser);
        }
    }

    @FXML
    private void deleteUser() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            deleteUser(selectedUser);
        }
    }

    @FXML
    private void viewUserDetails() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDetailsDialog.fxml"));
                Parent root = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("User Details - " + selectedUser.getName());
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(usersBtn.getScene().getWindow());
                dialogStage.setScene(new Scene(root));

                UserDetailsDialogController controller = loader.getController();
                controller.setUser(selectedUser);

                dialogStage.showAndWait();
            } catch (IOException e) {
                showError("Error opening user details dialog", e.getMessage());
            }
        }
    }

    // Course management methods
    @FXML
    private void showAddCourse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCourseDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add New Course");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(coursesBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            AddCourseDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening add course dialog", e.getMessage());
        }
    }

    @FXML
    private void searchCourses() {
        filterCourses();
    }

    @FXML
    private void refreshCourses() {
        courseSearchField.clear();
        courseSemesterFilter.setValue("All Semesters");
        loadCourses();
    }

    private void filterCourses() {
        try {
            String semesterFilter = courseSemesterFilter.getValue();
            String searchText = courseSearchField.getText().toLowerCase();

            ObservableList<Course> filteredCourses = FXCollections.observableArrayList();

            for (Course course : coursesList) {
                boolean matchesSemester = semesterFilter.equals("All Semesters") ||
                        semesterFilter.equals("Semester " + course.getSemester());
                boolean matchesSearch = searchText.isEmpty() ||
                        course.getCourseCode().toLowerCase().contains(searchText) ||
                        course.getCourseName().toLowerCase().contains(searchText);

                if (matchesSemester && matchesSearch) {
                    filteredCourses.add(course);
                }
            }

            coursesTable.setItems(filteredCourses);
        } catch (Exception e) {
            showError("Error filtering courses", e.getMessage());
        }
    }

    private void editCourse(Course course) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCourseDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Course - " + course.getCourseName());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(coursesBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            EditCourseDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);
            controller.setCourse(course);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening edit course dialog", e.getMessage());
        }
    }

    private void deleteCourse(Course course) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Course");
        alert.setHeaderText("Are you sure you want to delete this course?");
        alert.setContentText("Course: " + course.getCourseCode() + " - " + course.getCourseName() +
                "\nThis will also delete all related offerings and enrollments.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                dbManager.deleteCourse(course.getCourseId());
                loadCourses();
                showSuccess("Course deleted successfully");
            } catch (SQLException e) {
                showError("Error deleting course", e.getMessage());
            }
        }
    }

    @FXML
    private void editCourse() {
        Course selectedCourse = coursesTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            editCourse(selectedCourse);
        }
    }

    @FXML
    private void deleteCourse() {
        Course selectedCourse = coursesTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            deleteCourse(selectedCourse);
        }
    }

    @FXML
    private void viewCourseOfferings() {
        Course selectedCourse = coursesTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseOfferingsDialog.fxml"));
                Parent root = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Course Offerings - " + selectedCourse.getCourseName());
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(coursesBtn.getScene().getWindow());
                dialogStage.setScene(new Scene(root));

                CourseOfferingsDialogController controller = loader.getController();
                controller.setCourse(selectedCourse);
                controller.setAdminController(this);

                dialogStage.showAndWait();
            } catch (IOException e) {
                showError("Error opening course offerings dialog", e.getMessage());
            }
        }
    }

    // Semester management methods
    @FXML
    private void showAddSemester() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSemesterDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add New Semester");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(semestersBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            AddSemesterDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening add semester dialog", e.getMessage());
        }
    }

    private void editSemester(Semester semester) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditSemesterDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Semester");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(semestersBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            EditSemesterDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);
            controller.setSemester(semester);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening edit semester dialog", e.getMessage());
        }
    }

    private void deleteSemester(Semester semester) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Semester");
        alert.setHeaderText("Are you sure you want to delete this semester?");
        alert.setContentText("Semester: " + semester.getTerm() + " - " + semester.getStartDate() +
                "\nThis will also delete all related course offerings and enrollments.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                dbManager.deleteSemester(semester.getSemesterId());
                loadSemesters();
                showSuccess("Semester deleted successfully");
            } catch (SQLException e) {
                showError("Error deleting semester", e.getMessage());
            }
        }
    }

    @FXML
    private void editSemester() {
        Semester selectedSemester = semestersTable.getSelectionModel().getSelectedItem();
        if (selectedSemester != null) {
            editSemester(selectedSemester);
        }
    }

    @FXML
    private void deleteSemester() {
        Semester selectedSemester = semestersTable.getSelectionModel().getSelectedItem();
        if (selectedSemester != null) {
            deleteSemester(selectedSemester);
        }
    }

    // Enrollment management methods
    @FXML
    private void showAddEnrollment() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEnrollmentDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add New Enrollment");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(enrollmentsBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            AddEnrollmentDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening add enrollment dialog", e.getMessage());
        }
    }

    @FXML
    private void searchEnrollments() {
        filterEnrollments();
    }

    @FXML
    private void refreshEnrollments() {
        enrollmentSearchField.clear();
        enrollmentSemesterFilter.setValue("All Semesters");
        loadEnrollments();
    }

    private void filterEnrollments() {
        try {
            String semesterFilter = enrollmentSemesterFilter.getValue();
            String searchText = enrollmentSearchField.getText().toLowerCase();

            ObservableList<Enrollment> filteredEnrollments = FXCollections.observableArrayList();

            for (Enrollment enrollment : enrollmentsList) {
                boolean matchesSemester = semesterFilter.equals("All Semesters") ||
                        (enrollment.getSemesterTerm() + " - " + enrollment.getSemesterStartDate()).contains(semesterFilter.substring(0, Math.min(semesterFilter.length(), 10)));
                boolean matchesSearch = searchText.isEmpty() ||
                        enrollment.getStudentName().toLowerCase().contains(searchText);

                if (matchesSemester && matchesSearch) {
                    filteredEnrollments.add(enrollment);
                }
            }

            enrollmentsTable.setItems(filteredEnrollments);
        } catch (Exception e) {
            showError("Error filtering enrollments", e.getMessage());
        }
    }

    private void removeEnrollment(Enrollment enrollment) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove Enrollment");
        alert.setHeaderText("Are you sure you want to remove this enrollment?");
        alert.setContentText("Student: " + enrollment.getStudentName() +
                "\nCourse: " + enrollment.getCourseName() +
                "\nThis action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                dbManager.deleteEnrollment(enrollment.getEnrollmentId());
                loadEnrollments();
                showSuccess("Enrollment removed successfully");
            } catch (SQLException e) {
                showError("Error removing enrollment", e.getMessage());
            }
        }
    }

    @FXML
    private void removeEnrollment() {
        Enrollment selectedEnrollment = enrollmentsTable.getSelectionModel().getSelectedItem();
        if (selectedEnrollment != null) {
            removeEnrollment(selectedEnrollment);
        }
    }

    @FXML
    private void viewEnrollmentDetails() {
        Enrollment selectedEnrollment = enrollmentsTable.getSelectionModel().getSelectedItem();
        if (selectedEnrollment != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EnrollmentDetailsDialog.fxml"));
                Parent root = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Enrollment Details");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(enrollmentsBtn.getScene().getWindow());
                dialogStage.setScene(new Scene(root));

                EnrollmentDetailsDialogController controller = loader.getController();
                controller.setEnrollment(selectedEnrollment);

                dialogStage.showAndWait();
            } catch (IOException e) {
                showError("Error opening enrollment details dialog", e.getMessage());
            }
        }
    }

    // Assessment management methods
    @FXML
    private void showAddAssessmentType() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAssessmentTypeDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Assessment Type");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(assessmentsBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            AddAssessmentTypeDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening add assessment type dialog", e.getMessage());
        }
    }

    @FXML
    private void showAddCourseAssessment() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCourseAssessmentDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Course Assessment");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(assessmentsBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            AddCourseAssessmentDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening add course assessment dialog", e.getMessage());
        }
    }

    private void editAssessmentType(AssessmentType assessmentType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAssessmentTypeDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Assessment Type");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(assessmentsBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            EditAssessmentTypeDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);
            controller.setAssessmentType(assessmentType);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening edit assessment type dialog", e.getMessage());
        }
    }

    private void deleteAssessmentType(AssessmentType assessmentType) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Assessment Type");
        alert.setHeaderText("Are you sure you want to delete this assessment type?");
        alert.setContentText("Assessment Type: " + assessmentType.getName() +
                "\nThis will also delete all related course assessments.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                dbManager.deleteAssessmentType(assessmentType.getAssessmentTypeId());
                loadAssessmentTypes();
                showSuccess("Assessment type deleted successfully");
            } catch (SQLException e) {
                showError("Error deleting assessment type", e.getMessage());
            }
        }
    }

    private void editCourseAssessment(CourseAssessment courseAssessment) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCourseAssessmentDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Course Assessment");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(assessmentsBtn.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            EditCourseAssessmentDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdminController(this);
            controller.setCourseAssessment(courseAssessment);

            dialogStage.showAndWait();
        } catch (IOException e) {
            showError("Error opening edit course assessment dialog", e.getMessage());
        }
    }

    private void deleteCourseAssessment(CourseAssessment courseAssessment) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Course Assessment");
        alert.setHeaderText("Are you sure you want to delete this course assessment?");
        alert.setContentText("Assessment: " + courseAssessment.getAssessmentName() +
                "\nThis will also delete all student marks for this assessment.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                dbManager.deleteCourseAssessment(courseAssessment.getAssessmentId());
                loadCourseAssessments();
                showSuccess("Course assessment deleted successfully");
            } catch (SQLException e) {
                showError("Error deleting course assessment", e.getMessage());
            }
        }
    }

    // Report generation methods
    @FXML
    private void generateStudentListReport() {
        try {
            String report = dbManager.generateStudentListReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating student list report", e.getMessage());
        }
    }

    @FXML
    private void generateEnrollmentReport() {
        try {
            String report = dbManager.generateEnrollmentReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating enrollment report", e.getMessage());
        }
    }

    @FXML
    private void generatePerformanceReport() {
        try {
            String report = dbManager.generatePerformanceReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating performance report", e.getMessage());
        }
    }

    @FXML
    private void generateCourseListReport() {
        try {
            String report = dbManager.generateCourseListReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating course list report", e.getMessage());
        }
    }

    @FXML
    private void generateCourseOfferingReport() {
        try {
            String report = dbManager.generateCourseOfferingReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating course offering report", e.getMessage());
        }
    }

    @FXML
    private void generateFacultyWorkloadReport() {
        try {
            String report = dbManager.generateFacultyWorkloadReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating faculty workload report", e.getMessage());
        }
    }

    @FXML
    private void generateUserActivityReport() {
        try {
            String report = dbManager.generateUserActivityReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating user activity report", e.getMessage());
        }
    }

    @FXML
    private void generateSystemStatsReport() {
        try {
            String report = dbManager.generateSystemStatsReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating system statistics report", e.getMessage());
        }
    }

    @FXML
    private void generateAlertsReport() {
        try {
            String report = dbManager.generateAlertsReport();
            reportPreviewArea.setText(report);
        } catch (SQLException e) {
            showError("Error generating alerts report", e.getMessage());
        }
    }

    @FXML
    private void exportToPdf() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Report to PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("report_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf");

        File file = fileChooser.showSaveDialog(reportsBtn.getScene().getWindow());
        if (file != null) {
            try {
                ReportExporter.exportToPdf(reportPreviewArea.getText(), file.getAbsolutePath());
                showSuccess("Report exported to PDF successfully");
            } catch (Exception e) {
                showError("Error exporting to PDF", e.getMessage());
            }
        }
    }

    @FXML
    private void exportToExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Report to Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        fileChooser.setInitialFileName("report_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx");

        File file = fileChooser.showSaveDialog(reportsBtn.getScene().getWindow());
        if (file != null) {
            try {
                ReportExporter.exportToExcel(reportPreviewArea.getText(), file.getAbsolutePath());
                showSuccess("Report exported to Excel successfully");
            } catch (Exception e) {
                showError("Error exporting to Excel", e.getMessage());
            }
        }
    }

    @FXML
    private void printReport() {
        try {
            ReportPrinter.printReport(reportPreviewArea.getText());
            showSuccess("Report sent to printer successfully");
        } catch (Exception e) {
            showError("Error printing report", e.getMessage());
        }
    }

    @FXML
    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("You will be redirected to the login screen.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Close current stage
                Stage currentStage = (Stage) logoutButton.getScene().getWindow();
                currentStage.close();

                // Open login screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                Stage loginStage = new Stage();
                loginStage.setTitle("University Management System - Login");
                loginStage.setScene(new Scene(root));
                loginStage.show();

            } catch (IOException e) {
                showError("Error during logout", e.getMessage());
            }
        }
    }

    // Utility methods
    public void refreshAllTables() {
        loadUsers();
        loadCourses();
        loadSemesters();
        loadEnrollments();
        loadAssessmentTypes();
        loadCourseAssessments();
        loadDashboardData();
    }

    public DatabaseManager getDatabaseManager() {
        return dbManager;
    }

    private void showError(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private void showSuccess(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private void showWarning(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}