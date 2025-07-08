using System.ComponentModel.DataAnnotations;

namespace at_cnet.ViewModels
{
    public class LoginViewmodel
    {
        [Required]
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        [Display(Name = "Fazer Login toda vez")]
        public bool RememberMe { get; set; }

    }
}
