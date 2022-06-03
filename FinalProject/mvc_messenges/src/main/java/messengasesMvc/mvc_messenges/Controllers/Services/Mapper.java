package messengasesMvc.mvc_messenges.Controllers.Services;

import java.util.List;

import messengasesMvc.mvc_messenges.Model.ChatModel;
import messengasesMvc.mvc_messenges.Model.LetterModel;
import messengasesMvc.mvc_messenges.Model.UserModel;

public class Mapper {

	
	private List<LetterModel> listaMensaje;
	private List<ChatModel> listaChats;
	private List<UserModel> listaUsers;
	
	public Mapper (List<LetterModel> lista, List<ChatModel> chat, List<UserModel> users) {
		this.listaMensaje = lista;
		this.listaChats = chat;
		this.listaUsers = users;
	}

	
	public List<UserModel> getListaUsers() {
		return listaUsers;
	}


	public void setListaUsers(List<UserModel> listaUsers) {
		this.listaUsers = listaUsers;
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
