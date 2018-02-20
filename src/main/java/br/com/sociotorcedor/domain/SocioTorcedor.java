package br.com.sociotorcedor.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.google.common.base.MoreObjects;

/**
 * Faz o mapeamento entre a classe java e o documento MongoDB
 * @author : Ana Paula anapaulasilva1000@gmail.com
 */

@SuppressWarnings("deprecation")
@Document
public class SocioTorcedor {

    @Id
    private String id;

    @Indexed
    @Size(min=10, max=100, message="Nome tem tamanho entre 10 a 100 caracteres.")
    @NotNull(message="Insira o nome do sócio torcedor")
    @Field(value = "nomeCompleto")
    private String nomeCompleto;

    @Indexed(unique = true) // Validacao feita por e-mail, por isso deve ser unico
    @Size(min=10, max=100, message="E-mail tem tamanho entre 10 a 100 caracteres.")
    @NotNull(message="Insira o e-mail, esse campo é obrigatório.")
    @Field(value = "email")
    @Email(message = "O e-mail está inválido")
    private String email;

    @NotNull(message="Insira a data de nascimento.")
    @Field(value = "dataNascimento")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascimento;

    @Indexed
    @Size(min=5, max=100, message="Time do coração tem tamanho entre 5 a 100 caracteres.")
    @NotNull(message="Insira o nome do seu time do coração.")
    @Field(value = "timeCoracao")
    private String timeCoracao;

    SocioTorcedor(){

    }

    public SocioTorcedor(String nomeCompleto, String email, LocalDate dataNascimento, String timeCoracao) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.timeCoracao = timeCoracao;
    }

    public String getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTimeCoracao() {
        return timeCoracao;
    }

    public void setTimeCoracao(String timeCoracao) {
        this.timeCoracao = timeCoracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocioTorcedor that = (SocioTorcedor) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(getNomeCompleto(), that.getNomeCompleto()) &&
                java.util.Objects.equals(getEmail(), that.getEmail()) &&
                java.util.Objects.equals(getDataNascimento(), that.getDataNascimento()) &&
                java.util.Objects.equals(getTimeCoracao(), that.getTimeCoracao());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, getNomeCompleto(), getEmail(), getDataNascimento(), getTimeCoracao());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nomeCompleto", nomeCompleto)
                .add("email", email)
                .add("dataNascimento", dataNascimento)
                .add("timeCoracao", timeCoracao)
                .toString();
    }
}
