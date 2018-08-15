
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LifeRecord {
	private String name;
	private Date birthDate;
	private String birthPlace;
	private Date deathDate;

	private LifeRecord mother;
	private LifeRecord father;
	private ArrayList<LifeRecord> children;

	public LifeRecord(String name, Date birthDate, String birthPlace)
		throws BadDataException {
		if (name.equals(""))
			throw new BadDataException("Blank name is not allowed");
		this.name = name;

		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		children = new ArrayList<LifeRecord>();
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	@Override
	public String toString() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String result = name + " was born " + df.format(birthDate) + " in "
			+ birthPlace;
		if (deathDate != null)
			result += ", died " + df.format(deathDate);
		return result;
	}

	public LifeRecord getMother() {
		return mother;
	}

	public LifeRecord getFather() {
		return father;
	}

	public void setParents(LifeRecord m, LifeRecord f) {
		mother = m;
		father = f;
		if (mother != null)
			mother.addChild(this);
		if (father != null)
			father.addChild(this);
	}

	private void addChild(LifeRecord child) {
		children.add(child);
	}

	public int countSiblings() {
		int result = 0;

		if (this != null) {
			if (this.mother != null)
				result = this.mother.children.size() - 1;
			if (this.father != null)
				result += this.father.children.size() - 1;
		}
		return result;
	}

	public String maternalAncestors() {
		String result = "";
		LifeRecord current;

		current = this;
		while (current != null) {
			result += this.mother.name + " ";
			current = current.mother;
		}
		System.out.println("maternalAncestors:" + result);
		return result;
	}
}
