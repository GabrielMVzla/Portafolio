var entradaBlog;

window.onload = function () {
	entradaBlog = document.getElementsByClassName("entrada-blog");
};

document.addEventListener("keyup", () => {
	buscar();
});

function buscar() {
	var criterioBusqueda = document.getElementById("busqueda").value;
	if (criterioBusqueda.length > 0) {
		for (var i = 0; i < entradaBlog.length; i++) {
			var titulo = entradaBlog[i].children[0].children[1].children[0].textContent;
			if (comparar(criterioBusqueda, titulo)) {
				entradaBlog[i].classList.remove("esconder");
			} else {
				entradaBlog[i].classList.add("esconder");
			}
		}
	} else {
		reset();
	}
}

function comparar(criterio, titulo) {
	if (titulo.length < criterio.length) {
		return;
	}
	return titulo.substring(0, criterio.length).toUpperCase() == criterio.toUpperCase();
}

function reset() {
	for (var i = 0; i < entradaBlog.length; i++) {
		entradaBlog[i].classList.remove("esconder");
	}
}
