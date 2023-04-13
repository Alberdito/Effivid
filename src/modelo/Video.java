package modelo;

import java.util.Objects;

public class Video 
{
	private int cod_video;
	private String nombre;
	private int ref_producto;
	
	//CONSTRUCTORS
	public Video(int cod_video, String nombre, int ref_producto) {
		super();
		this.cod_video = cod_video;
		this.nombre = nombre;
		this.ref_producto = ref_producto;
	}
	
	public Video() {
		super();
	}

	//Getters and setters
	public int getCod_video() {
		return cod_video;
	}
	public void setCod_video(int cod_video) {
		this.cod_video = cod_video;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getRef_producto() {
		return ref_producto;
	}
	public void setRef_producto(int ref_producto) {
		this.ref_producto = ref_producto;
	}
	
	//HASH CODE (id_videos)
	@Override
	public int hashCode() {
		return Objects.hash(cod_video);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		return cod_video == other.cod_video;
	}
	
	//To String
	@Override
	public String toString() {
		return "Video [cod_video=" + cod_video + ", nombre=" + nombre + ", ref_producto=" + ref_producto + "]";
	}
	
	
	
	
}
