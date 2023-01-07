package gr.uom.java.xmi.decomposition;

import static gr.uom.java.xmi.decomposition.Visitor.stringify;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;

import gr.uom.java.xmi.LocationInfo;
import gr.uom.java.xmi.VariableDeclarationContainer;
import gr.uom.java.xmi.LocationInfo.CodeElementType;
import gr.uom.java.xmi.diff.CodeRange;

public class LeafExpression extends AbstractCodeFragment {
	private String expression;
	private LocationInfo locationInfo;

	public LeafExpression(CompilationUnit cu, String filePath, Expression expression, CodeElementType codeElementType, VariableDeclarationContainer container) {
    	this.locationInfo = new LocationInfo(cu, filePath, expression, codeElementType);
    	this.expression = stringify(expression);
	}

	@Override
	public LocationInfo getLocationInfo() {
		return locationInfo;
	}

	@Override
	public CodeRange codeRange() {
		return locationInfo.codeRange();
	}

	@Override
	public CompositeStatementObject getParent() {
		return null;
	}

	@Override
	public String getString() {
		return expression;
	}

	@Override
	public List<LeafExpression> getVariables() {
		return Collections.emptyList();
	}

	@Override
	public List<String> getTypes() {
		return Collections.emptyList();
	}

	@Override
	public List<VariableDeclaration> getVariableDeclarations() {
		return Collections.emptyList();
	}

	@Override
	public Map<String, List<AbstractCall>> getMethodInvocationMap() {
		return Collections.emptyMap();
	}

	@Override
	public List<AnonymousClassDeclarationObject> getAnonymousClassDeclarations() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getStringLiterals() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getNumberLiterals() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getNullLiterals() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getBooleanLiterals() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getTypeLiterals() {
		return Collections.emptyList();
	}

	@Override
	public Map<String, List<ObjectCreation>> getCreationMap() {
		return Collections.emptyMap();
	}

	@Override
	public List<LeafExpression> getInfixExpressions() {
		return Collections.emptyList();
	}

	@Override
	public List<String> getInfixOperators() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getArrayAccesses() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getPrefixExpressions() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getPostfixExpressions() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getThisExpressions() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getArguments() {
		return Collections.emptyList();
	}

	@Override
	public List<LeafExpression> getParenthesizedExpressions() {
		return Collections.emptyList();
	}

	@Override
	public List<TernaryOperatorExpression> getTernaryOperatorExpressions() {
		return Collections.emptyList();
	}

	@Override
	public List<LambdaExpressionObject> getLambdas() {
		return Collections.emptyList();
	}

	@Override
	public VariableDeclaration searchVariableDeclaration(String variableName) {
		return null;
	}

	@Override
	public VariableDeclaration getVariableDeclaration(String variableName) {
		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expression);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeafExpression other = (LeafExpression) obj;
		return Objects.equals(expression, other.expression);
	}
}
