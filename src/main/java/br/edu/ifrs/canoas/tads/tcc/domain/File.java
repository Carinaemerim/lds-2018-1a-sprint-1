package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;
import org.springframework.util.Base64Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	@Enumerated(EnumType.ORDINAL)
	private FileType fileType;

	@ManyToOne
	private User sender;

	@ManyToOne
	private User receiver;

	public String getPictureBase64() {
		return (content == null ? "/photos/no_image_available.png"
				: "data:image/png;base64," + Base64Utils.encodeToString(content));
	}

}
