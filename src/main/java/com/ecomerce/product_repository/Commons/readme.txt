

1: AuthUtils is made @Service as we need bean(OBJ) everywhere

2: for Validate Token (in user service) return type is UserDTO
    for Validate Token (in product service) but we dont have any UserDTO class in product service
    so we just copied that class from user service to product service
    and removed other fields like List<Role> role
    etc;

    for interservice communication we need RestTempplate
    which let us comunicate to other services or api over http methords
