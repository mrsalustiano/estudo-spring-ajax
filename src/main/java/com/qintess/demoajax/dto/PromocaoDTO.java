package com.qintess.demoajax.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import com.qintess.demoajax.domain.Categoria;

public class PromocaoDTO {

	@NotNull
    private Long id;

    @NotBlank(message = "Um título é requerido")
    private String titulo;
    
    private String descricao;

    @NotBlank(message = "Uma imagem é requerida")
    private String linkImagem;

    @NotNull(message = "O preço é requerido")
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal preco;

    @NotNull(message = "Uma categoria é requerida")
    private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "PromocaoDTO [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", linkImagem="
				+ linkImagem + ", preco=" + preco + ", categoria=" + categoria + "]";
	}
}
