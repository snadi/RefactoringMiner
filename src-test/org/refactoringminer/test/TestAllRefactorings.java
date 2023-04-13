package org.refactoringminer.test;

import net.joshka.junit.json.params.JsonFileSource;
import org.eclipse.jgit.lib.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.test.RefactoringPopulator.Refactorings;
import org.refactoringminer.test.RefactoringPopulator.Systems;

import org.junit.jupiter.api.Test;
import org.refactoringminer.util.GitServiceImpl;
import org.refactoringminer.utils.RefactoringJsonConverter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestAllRefactorings {

	@Test
	public void testAllRefactorings() throws Exception {
		GitHistoryRefactoringMinerImpl detector = new GitHistoryRefactoringMinerImpl();
		TestBuilder test = new TestBuilder(detector, "tmp1", Refactorings.All.getValue());
		RefactoringPopulator.feedRefactoringsInstances(Refactorings.All.getValue(), Systems.FSE.getValue(), test);
		test.assertExpectations(11657, 23, 269);
	}

	@ParameterizedTest
	@JsonFileSource(resources = "/data.json")
	public void testAllRefactoringsParameterized(@ConvertWith(RefactoringJsonConverter.class) RefactoringPopulator.Root testCase) throws Exception {
		GitHistoryRefactoringMinerImpl detector = new GitHistoryRefactoringMinerImpl();
		GitService gitService = new GitServiceImpl();
		String folder = "tmp1/" + testCase.repository.substring(testCase.repository.lastIndexOf('/') + 1, testCase.repository.lastIndexOf('.'));
		try (Repository rep = gitService.cloneIfNotExists(folder, testCase.repository)) {
			detector.detectAtCommit(rep, testCase.sha1, new RefactoringHandler() {
				Set<String> refactorings = null;

				@Override
				public boolean skipCommit(String commitId) {
					return commitId != testCase.sha1;
				}

				@Override
				public void handle(String commitId, List<Refactoring> refactorings) {
					this.refactorings = refactorings.stream().map(refactoring -> refactoring.getRefactoringType().getDisplayName()).collect(Collectors.toSet());
					testCase.refactorings.forEach(refactoring -> {
						Assertions.assertTrue(this.refactorings.contains(refactoring.type), String.format("Refactoring %s not found in commit %s (%s)%n", refactoring.type, testCase.sha1, this.refactorings));
					});
				}
			});
		}
		catch (Exception e) {
			Assertions.fail(e);
		}
	}
}
