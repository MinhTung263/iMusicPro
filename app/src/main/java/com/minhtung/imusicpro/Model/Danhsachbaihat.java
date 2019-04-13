package com.minhtung.imusicpro.Model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Danhsachbaihat implements Parcelable {

    @SerializedName("idBH")
    @Expose
    private String idBH;
    @SerializedName("TenBH")
    @Expose
    private String tenBH;
    @SerializedName("CaSi")
    @Expose
    private String caSi;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("LinkBH")
    @Expose
    private String linkBH;

    protected Danhsachbaihat(Parcel in) {
        idBH = in.readString();
        tenBH = in.readString();
        caSi = in.readString();
        hinhAnh = in.readString();
        linkBH = in.readString();
    }

    public static final Creator<Danhsachbaihat> CREATOR = new Creator<Danhsachbaihat>() {
        @Override
        public Danhsachbaihat createFromParcel(Parcel in) {
            return new Danhsachbaihat(in);
        }

        @Override
        public Danhsachbaihat[] newArray(int size) {
            return new Danhsachbaihat[size];
        }
    };

    public String getIdBH() {
        return idBH;
    }

    public void setIdBH(String idBH) {
        this.idBH = idBH;
    }

    public String getTenBH() {
        return tenBH;
    }

    public void setTenBH(String tenBH) {
        this.tenBH = tenBH;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getLinkBH() {
        return linkBH;
    }

    public void setLinkBH(String linkBH) {
        this.linkBH = linkBH;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBH);
        dest.writeString(tenBH);
        dest.writeString(caSi);
        dest.writeString(hinhAnh);
        dest.writeString(linkBH);
    }
}