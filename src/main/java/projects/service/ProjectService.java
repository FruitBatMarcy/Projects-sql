package projects.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

public class ProjectService {

	private ProjectDao projectDao = new ProjectDao();
	
	/*
	 * Tells DAO to insert project
	 */
	public Project addProjcet(Project project) {
		return projectDao.insertProject(project);
	}
	
	/*
	 * Tells DAO to get all projects
	 */
	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
	}

	public Project fetchProjectById(Integer projId) {

		return projectDao.fetchProjectById(projId).orElseThrow( () ->
			new NoSuchElementException("Project with project ID=" + projId + " does not exist"));
	}
}
