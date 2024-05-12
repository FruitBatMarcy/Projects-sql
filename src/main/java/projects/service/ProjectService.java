package projects.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

public class ProjectService {

	private ProjectDao projectDao = new ProjectDao();
	
	public Project addProjcet(Project project) {
		return projectDao.insertProject(project);
	}
}
