package com.chan.chang.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.common.entity.Result;
import com.chan.chang.common.entity.StatusCode;
import com.chan.chang.goods.pojo.Album;
import com.chan.chang.goods.service.AlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {
    final
    AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Album>> findAlbumPageByCondition(
            @RequestBody(required = false) Album album,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        IPage<Album> albumIPage = albumService.findAlbumPageByCondition(album, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", albumIPage);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Album>> findAlbumPages(@PathVariable("page") int page, @PathVariable("size") int size) {
        IPage<Album> albumIPage = albumService.findAlbumPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", albumIPage);
    }

    @GetMapping("/search")
    public Result<List<Album>> findAlbumByCondition(@RequestBody(required = false) Album album) {
        List<Album> albums = albumService.findAlbumByCondition(album);
        return new Result<>(true, StatusCode.OK, "查询成功", albums);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteAlbumByID(@PathVariable("id") Long id) {
        albumService.deleteById(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    @PutMapping("/{id}")
    public Result<Void> updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {
        albumService.updateAlbum(id, album);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    @PostMapping
    public Result<Void> addAlbum(@RequestBody Album album) {
        albumService.addAlbum(album);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    @GetMapping
    public Result<List<Album>> findAllAlbum() {
        List<Album> albums = albumService.findAlbum();
        return new Result<>(true, StatusCode.OK, "查询成功", albums);
    }

    @GetMapping("/{id}")
    public Result<Album> findAlbumById(@PathVariable Long id) {
        Album album = albumService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", album);
    }
}
