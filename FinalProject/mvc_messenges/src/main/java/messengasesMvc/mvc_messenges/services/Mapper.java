package messengasesMvc.mvc_messenges.services;

import java.util.List;

import messengasesMvc.mvc_messenges.Model.ChatModel;
import messengasesMvc.mvc_messenges.Model.LetterModel;

public class Mapper {

	
	private List<LetterModel> listaMensaje;
	private List<ChatModel> listaChats;
	
	public Mapper (List<LetterModel> lista, List<ChatModel> chat) {
		this.listaMensaje = lista;
		this.listaChats = chat;
	}

	
	public List<ChatModel> getListaChats() {
		return listaChats;
	}

	public void setListaChats(List<ChatModel> listaChats) {
		this.listaChats = listaChats;
	}

	public List<LetterModel> getListaMensaje() {
		return listaMensaje;
	}


	public void setListaMensaje(List<LetterModel> listaMensaje) {
		this.listaMensaje = listaMensaje;
	}
	
	
} 
