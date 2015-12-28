package rent.client.data;

import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.DateFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.StringFieldDef;

public class RecordDefs {
	
	public static RecordDef getRecordDpto() {
		return new RecordDef(new FieldDef[]{
			new IntegerFieldDef("idDpto"),
            new StringFieldDef("presentacionCorta"),
            new StringFieldDef("descripcion"),
            new StringFieldDef("barrio"),
            new IntegerFieldDef("metrosCuadrados"),
            new IntegerFieldDef("cantAmbientes"),
            new StringFieldDef("rutaFotoPrincipal"),
            new DateFieldDef("fechaAlta", "timestamp"),
            new BooleanFieldDef("activo")
		});
	}
	
}
