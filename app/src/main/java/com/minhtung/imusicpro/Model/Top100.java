package com.minhtung.imusicpro.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Top100 {

@SerializedName("idTop")
@Expose
private String idTop;
@SerializedName("TenTop")
@Expose
private String tenTop;
@SerializedName("HinhAnhTop")
@Expose
private String hinhAnhTop;

public String getIdTop() {
return idTop;
}

public void setIdTop(String idTop) {
this.idTop = idTop;
}

public String getTenTop() {
return tenTop;
}

public void setTenTop(String tenTop) {
this.tenTop = tenTop;
}

public String getHinhAnhTop() {
return hinhAnhTop;
}

public void setHinhAnhTop(String hinhAnhTop) {
this.hinhAnhTop = hinhAnhTop;
}

}