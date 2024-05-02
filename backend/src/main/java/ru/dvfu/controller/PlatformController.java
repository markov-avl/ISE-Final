package ru.dvfu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dvfu.service.PlatformService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/platforms")
public class PlatformController {

    private final PlatformService platformService;

//    @GetMapping("/{id}")
//    public ResponseEntity<ProfessionTypeDto> getById(@PathVariable Long id) {
//        Platform professionType = platformService.getById(id);
//        ProfessionTypeDto professionTypeDto = professionTypeMapper.toDto(professionType);
//
//        return ResponseEntity.ok(professionTypeDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<PageDto<ProfessionTypeDto>> getAll(
//            @Valid PageParamsDto pageParamsDto,
//            @Valid SortParamsDto sortParamsDto
//    ) {
//        PageRequest pageRequest = PageUtil.request(pageParamsDto);
//        Sort sort = SortUtil.request(sortParamsDto);
//
//        Page<Platform> professionTypes = platformService.getAll(pageRequest.withSort(sort));
//        PageDto<ProfessionTypeDto> professionTypesDto = professionTypeMapper.toPageDto(professionTypes);
//
//        return ResponseEntity.ok(professionTypesDto);
//    }

}
