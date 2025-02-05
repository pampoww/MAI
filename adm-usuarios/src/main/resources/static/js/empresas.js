import * as main from "./main.js"

const secContentInfo = document.querySelector(".secondary-content__info")
const btn_add_tel = document.querySelector(".up__contatos__info > .telefone > .btn__add")
const btn_add_site = document.querySelector(".up__contatos__info > .site > .btn__add")
const btn_excluir_empresa = document.querySelector(".info__view__footer__button")

const cardEmpresa = document.querySelectorAll("div[data-id-card-empresa]")

cardEmpresa.forEach(card => {
    card.addEventListener("click", (e) => {
        e.preventDefault()
        const idEmpresa = e.currentTarget.getAttribute("data-id-card-empresa")

        if (idEmpresa !== null) {
            window.location.replace(`/empresas/${idEmpresa}/mensagem`)
        }
    })
})

btn_excluir_empresa.addEventListener("click", async () => {
    const decisao = confirm("Quer mesmo excluir essa empresa?")
    if (decisao) {
        await fetch(`/adm/empresa/${secContentInfo.getAttribute("data-id")}/delete`, {
            headers: {
                "Content-Type": "application/json"
            }
        }).then(() => window.location.reload())
    }
})

btn_add_tel.addEventListener("click", async () => {
    const telefone = prompt("Digite o novo telefone sem o DDD (tamanho: 9)")
    await fetch(`/empresa/update/tel/${secContentInfo.getAttribute("data-id")}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"  // Define que o conteúdo será JSON
        },
        body: JSON.stringify({telefone})
    }).then((res) => {
        if (res.status !== 200) {
            alert("Telefone inválido")
        } else {
            window.location.reload()
        }
    })
})

btn_add_site.addEventListener("click", async () => {
    const site = prompt("Qual o novo site")
    await fetch(`empresa/update/site/${secContentInfo.getAttribute("data-id")}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"  // Define que o conteúdo será JSON
        },
        body: JSON.stringify({site})
    }).then((res) => {
        if (res.status !== 200) {
            alert("Site inválido")
        } else {
            window.location.reload()
        }
    })
})
