
package com.mycompany.eyemarket;

public class Usuario {
    private String nomeCompleto;
    private String email;
    private String senha;
    private Integer Telefone;
    private String cargo;
    private Integer fkEmpresa;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTelefone() {
        return Telefone;
    }

    public void setTelefone(Integer Telefone) {
        this.Telefone = Telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nomeCompleto=" + nomeCompleto + ", email=" + email + ", senha=" + senha + ", Telefone=" + Telefone + ", cargo=" + cargo + ", fkEmpresa=" + fkEmpresa + '}';
    }
    
    
}
