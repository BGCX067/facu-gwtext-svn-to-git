package rent.client.data;

public final class Templates {

	public static String[] getThumbTemplate() {
		// the thumb nail template for the main view
		return new String[]{
                "<tpl for='.'>",
                "<div class='thumb-wrap' id='{presentacionCorta}'>",
                "<div class='thumb'><img src='{rutaFotoPrincipal}' title='{presentacionCorta}'></div>",
                "<span>{presentacionCorta}</span></div>",
                "</tpl>",
                "<div class='x-clear'></div>"};

	}
	
	public static String[] getDetailTemplate() {
		 // the detail template for the selected image
        return new String[]{
                "<tpl for='.'>",
                "<div class='details'><img src='{rutaFotoPrincipal}'>",
                "<div class='details-info'><b>Image Name:</b>",
                "<span>{presentacionCorta}</span><b>Ambientes:</b>",
                "<span>{cantAmbientes}</span><b>Barrio:</b>",
                "<span>{barrio}</span><b>Descripcion:</b>",
                "<span>{descripcion}</span><b>Fecha Alta:</b>",
                "<span>{fechaAlta}</span></div></div>",
                "</tpl>",
                "<div class='x-clear'></div>"};

	}
}
