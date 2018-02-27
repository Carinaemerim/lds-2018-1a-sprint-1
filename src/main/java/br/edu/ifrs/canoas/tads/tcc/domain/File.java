package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;
import org.springframework.util.Base64Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class File {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private String filename;
	@Column(length = 2147483647)
	private byte[] content;
	private String contentType;
	private Date createdOn;

	public String getPictureBase64() {
		return (content == null ? "/photos/no_image_available.png"
				: "data:image/png;base64," + Base64Utils.encodeToString(content));
	}

}
