package prg.perscholas.FileUploadDemo.FileUploadExample;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {

	@Autowired
	FileService fileService;
	
	@GetMapping("/")
	public String indexPage() {
		return "upload";
	}


	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws IOException {

		String filenames  = file.getOriginalFilename();
		System.out.println("FILE="+filenames);
		//model.addAttribute("filename", filenames);.
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "please select a file to upload.");
			System.out.println("IS EMPTY");
			return "redirect:/";
		} else {fileService.uploadFile(file);
			System.out.println("UPLOAD FILE");
			//redirectAttributes.addAttribute("message","IT WORKED");
			redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!, file.");
		}
		return "redirect:/";
		//return "upload_success";
	}




}
