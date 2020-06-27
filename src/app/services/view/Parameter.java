package app.services.view;

public class Parameter<E> {
	private E element;
	public Parameter(){
	}
	public Parameter(E element){
		this.element = element;
	}

	public E getElement() {
		return element;
	}
}
