public class CreateTable {

	private String TbName;
	private String[] Column;
	private String[] Type;
	private Boolean[] NotNull;
	private Boolean[] PrimaryKey;
	private int columns;
	
	public CreateTable(String tbname) {
		Column = new String[256];
		Type = new String[256];
		NotNull = new Boolean[256];
		PrimaryKey = new Boolean[256];
		columns = -1;
		TbName = tbname;
	}
	public String toString() {
		String SQL = "CREATE TABLE " + TbName + "\n";
		String Table = "";
		for (int i = 0; i <= columns; ++i) {
			Table += "\t" + Column[i] + " " + Type[i] + (PrimaryKey[i]?" primary key":"") + (NotNull[i]?" not null":"") + ",\n";
		}
		if (Table.length() > 0) {
			Table = Table.substring(0, Table.length() - 2);
		}
		SQL += "(\n" + Table + "\n)";
		return(SQL);
	}
	
	public CreateTable column(String column) {
		columns++;
		Column[columns] = column;
		NotNull[columns] = false;
		PrimaryKey[columns] = false;
		return(this);
	}

	public CreateTable character(int num) {
		Type[columns] = "char(" + Integer.toString(num) + ")";
		return(this);
	}

	public CreateTable varchar(int num) {
		Type[columns] = "varchar(" + Integer.toString(num) + ")";
		return(this);
	}

	public CreateTable date() {
		Type[columns] = "date";
		return(this);
	}

	public CreateTable onDeleteCascade() {
		Type[columns] += " on delete cascade";
		return(this);
	}

	public CreateTable foreignConstraint(String str1, String str2) {
		Type[columns] += " references " + str1 + "(" + str2 + ")";
		return(this);
	}

	public CreateTable notNull() {
		NotNull[columns] = true;
		return(this);
	}

	public CreateTable primaryKey() {
		PrimaryKey[columns] = true;
		return(this);
	}

}
